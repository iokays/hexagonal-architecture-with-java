package com.iokays.sample;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.jdbc.store.JdbcChannelMessageStore;
import org.springframework.messaging.Message;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class SampleTest {

    @Resource
    private JdbcChannelMessageStore jdbcChannelMessageStore;

    @Test
    @Transactional
    void test() {
        final var groupId = "order";
        jdbcChannelMessageStore.addMessageToGroup(groupId, new DefaultJacksonMessage("1", null));
        jdbcChannelMessageStore.addMessageToGroup(groupId, new DefaultJacksonMessage("2", null));

        //断言消息的个数为2
        Assertions.assertEquals(2, jdbcChannelMessageStore.messageGroupSize(groupId));

        final Message<?> message = jdbcChannelMessageStore.pollMessageFromGroup(groupId);

        //断言消息内容为1
        Assertions.assertEquals("1", message.getPayload());

        //断言消息的个数为1, 因为调用DEL语句删除了。
        Assertions.assertEquals(1, jdbcChannelMessageStore.messageGroupSize(groupId));

    }

}

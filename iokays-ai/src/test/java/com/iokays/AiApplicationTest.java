package com.iokays;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class AiApplicationTest {

    @Resource
    private ChatClient chatClient;

    @Test
    void test() {
        log.info("hello world");
        final String content = chatClient.prompt().user("帮忙提供一份长度为2048位的RSA的公钥和私钥").call().content();
        log.info("content: {}", content);
    }

}
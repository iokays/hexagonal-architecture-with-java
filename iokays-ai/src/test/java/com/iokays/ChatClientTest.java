package com.iokays;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.template.st.StTemplateRenderer;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
class ChatClientTest {

    @Resource
    private ChatClient chatClient;

    @Test
    void test() {
        final Flux<String> content = chatClient.prompt().user("What is Spring").stream().content();
        // 收集所有响应块并打印
        List<String> allChunks = content.collectList().block();
        log.info("Full content: {}", String.join("", CollectionUtils.emptyIfNull(allChunks)));
    }

}
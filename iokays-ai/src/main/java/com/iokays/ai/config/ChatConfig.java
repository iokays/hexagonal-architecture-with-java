package com.iokays.ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
class ChatConfig {

    @Bean
    @Primary
    ChatClient chatClient(ChatClient.Builder builder) {
        return builder.build();
    }

    @Bean("chatBotClient")
    ChatClient chatBotClient(ChatClient.Builder builder) {
        return builder.build();
    }
}
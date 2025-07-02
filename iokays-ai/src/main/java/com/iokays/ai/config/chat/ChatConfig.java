package com.iokays.ai.config.chat;

import jakarta.annotation.Nullable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ChatConfig {

    @Bean
    ChatClient chatClient(ChatClient.Builder builder,
                          ChatMemory chatMemory,
                          @Nullable ToolCallbackProvider toolCallbackProvider) {
        builder.defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build());
        if (toolCallbackProvider != null) {
            builder.defaultToolCallbacks(toolCallbackProvider.getToolCallbacks());
        }
        return builder.build();
    }


}
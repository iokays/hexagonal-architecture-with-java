package com.iokays.ai.config.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.mcp.AsyncMcpToolCallback;
import org.springframework.ai.mcp.AsyncMcpToolCallbackProvider;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ChatConfig {

    @Bean
    ChatClient chatClient(ChatClient.Builder builder,
                          ChatMemory chatMemory,
                          ToolCallbackProvider toolCallbackProvider) {
        return builder
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultToolCallbacks(toolCallbackProvider.getToolCallbacks())
                .build();
    }

}
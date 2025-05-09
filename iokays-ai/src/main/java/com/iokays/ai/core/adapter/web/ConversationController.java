package com.iokays.ai.core.adapter.web;

import com.iokays.ai.core.adapter.web.model.ConversationModel;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/conversation")
@AllArgsConstructor
public class ConversationController {

    private final ChatClient chatBotClient;

    @GetMapping
    public Flux<String> getConversation(ConversationModel model) {
        return this.conversation(model);
    }

    @PostMapping
    public Flux<String> conversation(@RequestBody ConversationModel model) {
        final var conversationId = Optional.ofNullable(model.conversationId())
                .filter(StringUtils::isNotBlank)
                .orElseGet(() -> UUID.randomUUID().toString());

        return chatBotClient.prompt()
                .user(model.message())
                .advisors(a -> a.param(AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY, conversationId))
                .stream()
                .content();

    }

}

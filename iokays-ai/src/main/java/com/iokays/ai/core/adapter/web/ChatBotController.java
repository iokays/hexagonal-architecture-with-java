
package com.iokays.ai.core.adapter.web;

import com.iokays.ai.core.adapter.web.model.ConversationModel;
import com.iokays.ai.core.adapter.web.model.CreateConversationModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController("chatBot")
public class ChatBotController {

    private final ChatClient chatBotClient;

    public ChatBotController(@Qualifier("chatBotClient") ChatClient chatBotClient) {
        this.chatBotClient = chatBotClient;
    }

    @PostMapping("/conversation/create")
    public Mono<String> create(@RequestBody CreateConversationModel model) {
        return Mono.just("hello world");
    }

    @PostMapping("/conversation")
    public Flux<String> conversation(@RequestBody ConversationModel model) {

        //历史消息 用例
        final List<Message> messages = List.of(
                new SystemMessage("你是一个天气预报助手。"),
                new UserMessage("东莞天气温多少度？"),
                new AssistantMessage("东莞今天气温 25℃，晴。")
        );
        return chatBotClient
                .prompt()
                .messages(messages)
                .user(model.message())
                .stream()
                .content();
    }

}

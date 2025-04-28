package com.iokays.ai.core.adapter.web;

import com.iokays.ai.core.adapter.web.model.ConversionModel;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.validation.Valid;
import java.util.Map;


@RestController("chatBot")
class ChatBotController {

	private final ChatClient chatClient;

    ChatBotController(@Qualifier("chatBotClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @PostMapping("/conversation")
	Flux<String> conversation(@RequestBody ConversionModel model) {
        Validate.notEmpty(ArrayUtils.toArray(
                model.conversationId(),
                model.message()
        ));
        return chatClient.prompt().user(model.message()).stream().content();
	}

}
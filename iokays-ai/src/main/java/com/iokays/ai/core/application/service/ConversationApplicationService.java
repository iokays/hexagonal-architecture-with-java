
package com.iokays.ai.core.application.service;

import com.iokays.ai.core.domain.conversation.Conversation;
import com.iokays.ai.core.domain.conversation.ConversationId;
import com.iokays.ai.core.domain.conversation.ConversationRepository;
import com.iokays.ai.core.domain.conversation.command.CreateConversation;
import com.iokays.common.core.service.ApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class ConversationApplicationService implements ApplicationService {

    private final ConversationRepository conversationRepository;

    public ConversationId save(CreateConversation command) {
        final var conversaton = new Conversation(command.authenticatedUser(), command.act(), command.prompt());
        conversationRepository.save(conversaton);
        return conversaton.conversationId();
    }

}

package com.iokays.ai.core.domain.chatbot;

import com.iokays.ai.core.domain.actor.Actor;
import com.iokays.ai.core.domain.conversation.ConversationId;
import com.iokays.common.domain.jpa.IdentifiedValueObject;
import jakarta.persistence.*;

@Entity
@Table(name = "t_message")
public class Message extends IdentifiedValueObject<Actor> {

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "conversation_id", length = 40, nullable = false))
    private ConversationId conversationId;

    @Enumerated(EnumType.STRING)
    private MessageRole role;

    private String message;

    @Override
    public boolean sameValueAs(Actor other) {
        return false;
    }

}
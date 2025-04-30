
package com.iokays.ai.core.domain.conversation;

import com.iokays.common.core.security.AuthenticatedUser;
import com.iokays.common.domain.jpa.AbstractAggregateRoot;
import jakarta.persistence.*;
import org.apache.commons.lang3.StringUtils;

@Entity
@Table(name = "t_conversation")
public class Conversation extends AbstractAggregateRoot<Conversation> {

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "conversation_id", length = 40, nullable = false))
    private ConversationId conversationId;
    private String userId;
    private String act;
    private String prompt;

    protected Conversation() {
        super();
    }

    public Conversation(final AuthenticatedUser user, final String act, final String prompt) {
        this();
        this.conversationId = ConversationId.makeConversationId();
        this.userId = user.userId();
        this.act = StringUtils.defaultString(act);
        this.prompt = StringUtils.defaultString(prompt);
    }

    @Override
    public boolean sameIdentityAs(Conversation other) {
        return false;
    }

    public ConversationId conversationId() {
        return conversationId;
    }

}
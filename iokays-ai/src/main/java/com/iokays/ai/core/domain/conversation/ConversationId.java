
package com.iokays.ai.core.domain.conversation;

import com.iokays.common.domain.jpa.AbstractId;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class ConversationId extends AbstractId {

    protected ConversationId() {
        super();
    }

    public ConversationId(String id) {
        super(id);
    }

    public static ConversationId makeConversationId() {
        String id = UUID.randomUUID().toString().toLowerCase();
        log.info("makeConversationId: {}", id);
        return new ConversationId(id);
    }

}
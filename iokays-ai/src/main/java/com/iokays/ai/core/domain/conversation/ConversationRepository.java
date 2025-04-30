

package com.iokays.ai.core.domain.conversation;

import com.iokays.common.core.infrastructure.Repository;

public interface ConversationRepository extends Repository {

    Conversation save(Conversation conversation);
}
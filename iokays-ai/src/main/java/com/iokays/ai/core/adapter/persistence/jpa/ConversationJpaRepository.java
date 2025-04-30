package com.iokays.ai.core.adapter.persistence.jpa;

import com.iokays.ai.core.domain.conversation.Conversation;
import com.iokays.ai.core.domain.conversation.ConversationRepository;
import com.iokays.common.core.adapter.DrivenAdapter;
import org.springframework.data.jpa.repository.JpaRepository;


@DrivenAdapter
public interface ConversationJpaRepository extends JpaRepository<Conversation, Long>, ConversationRepository {
}

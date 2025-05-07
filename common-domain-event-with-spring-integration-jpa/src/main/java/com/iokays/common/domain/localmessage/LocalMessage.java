package com.iokays.common.domain.localmessage;

import com.iokays.common.core.event.DomainEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.apache.commons.lang3.Validate;
import org.springframework.messaging.support.GenericMessage;

/**
 * 本地消息表
 */
@Entity
@Table(name = "t_local_message")
public class LocalMessage extends AbstractLocalMessage<LocalMessage> {

    protected LocalMessage() {
    }

    public LocalMessage(final GenericMessage<?> message) {
        super(message);
    }

    @Override
    protected void validateMessageBytes(Object body) {
        super.validateMessageBytes(body);
        Validate.isTrue(body instanceof DomainEvent, "message.body 必须是 DomainEvent.");
    }
}

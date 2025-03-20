package com.iokays.authorization.core.domain.user;

import com.iokays.authorization.core.domain.user.event.UserDomainEvent;
import com.iokays.common.domain.localmessage.AbstractLocalMessage;
import com.iokays.common.domain.localmessage.LocalMessage;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.apache.commons.lang3.Validate;
import org.springframework.messaging.support.GenericMessage;

@Entity
@Table(name = "t_user_local_message")
public class UserLocalMessage extends AbstractLocalMessage<LocalMessage> {

    protected UserLocalMessage() {
        super();
    }

    public UserLocalMessage(final GenericMessage<?> message) {
        super(message);
    }

    @Override
    protected void validateMessageBytes(Object body) {
        super.validateMessageBytes(body);
        Validate.isTrue(body instanceof UserDomainEvent<?>, "message.body 必须是 UserDomainEvent.");
    }

}

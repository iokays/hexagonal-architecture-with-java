package com.iokays.common.domain.localmessage;

import com.iokays.common.core.domain.Entity;
import com.iokays.common.core.event.DomainEvent;
import com.iokays.common.domain.jpa.IdentifiedDomainObject;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.type.TrueFalseConverter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.messaging.support.GenericMessage;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 本地消息表
 *
 */
@DynamicInsert
@DynamicUpdate
@Access(AccessType.FIELD)
@jakarta.persistence.Entity
@Table(name = "t_local_message")
@EntityListeners(AuditingEntityListener.class)
@SoftDelete(columnName = "deleted", converter = TrueFalseConverter.class)
public class LocalMessage extends IdentifiedDomainObject implements Entity<LocalMessage> {

    @AttributeOverride(name = "id", column = @Column(name = "local_message_id", length = 40, nullable = false))
    private LocalMessageId localMessageId;

    /**
     * 消息内容 json格式字符串的字节数组
     */
    @Column(nullable = false, updatable = false)
    private byte[] messageBytes;

    /**
     * 创建时间，由SpringDataJpa自动生成。
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    /**
     * 修改时间，由SpringDataJpa自动生成。
     */
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime lastModifiedDate;

    protected LocalMessage() {
        super();
    }

    public LocalMessage(final GenericMessage<? extends DomainEvent<?>> message) {
        this();
        this.localMessageId = new LocalMessageId(Objects.requireNonNull(message.getHeaders().getId()).toString());
        this.messageBytes = DomainEventInputMessageMapper.toBytes(message.getPayload());
    }

    @Override
    public boolean sameIdentityAs(LocalMessage other) {
        return localMessageId.equals(other.localMessageId);
    }

}

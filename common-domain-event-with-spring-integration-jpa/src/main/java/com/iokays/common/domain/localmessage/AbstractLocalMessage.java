package com.iokays.common.domain.localmessage;

import com.iokays.common.core.domain.Entity;
import com.iokays.common.domain.jpa.IdentifiedDomainObject;
import jakarta.persistence.*;
import org.apache.commons.lang3.Validate;
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
 */
@DynamicInsert
@DynamicUpdate
@MappedSuperclass
@Access(AccessType.FIELD)
@EntityListeners(AuditingEntityListener.class)
@SoftDelete(columnName = "deleted", converter = TrueFalseConverter.class)
public abstract class AbstractLocalMessage<T extends AbstractLocalMessage<?>> extends IdentifiedDomainObject implements Entity<T> {

    @Embedded
    @AttributeOverride(name = "code", column = @Column(name = "message_code", length = 64, nullable = false, updatable = false))
    private LocalMessageCode messageCode;


    private String messageType;

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

    protected AbstractLocalMessage() {
        super();
    }

    protected AbstractLocalMessage(final GenericMessage<?> message) {
        this();
        this.messageCode = new LocalMessageCode(Objects.requireNonNull(message.getHeaders().getId()).toString());

        final Object payload = message.getPayload();
        this.messageType = payload.getClass().getSimpleName();
        this.validateMessageBytes(payload);
        this.messageBytes = LocalMessageMapper.toBytes(payload);
    }

    protected LocalMessageCode localMessageCode() {
        return this.messageCode;
    }

    protected void validateMessageBytes(Object body) {
        Validate.notNull(body, "The message body is required.");
    }

    @Override
    public boolean sameIdentityAs(T other) {
        return this.getClass().equals(other.getClass()) && this.localMessageCode().equals(other.localMessageCode());
    }
}

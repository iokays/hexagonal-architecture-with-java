package com.iokays.sample.domain.customer.event;

import com.iokays.common.core.event.DomainEvent;
import com.iokays.common.core.event.EventId;
import com.iokays.sample.domain.customer.CustomerId;
import com.iokays.sample.domain.customer.FullName;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 客户已注册的领域事件
 *
 * @param eventId      {@link EventId} 事件标识
 * @param registeredAt {@link Instant} 事件发生时间
 * @param customerId   {@link CustomerId} 客户标识
 */
public record CustomerFullNameUpdated(EventId eventId, CustomerId customerId,
                                      FullName fullName,
                                      LocalDateTime registeredAt) implements DomainEvent<CustomerFullNameUpdated> {

    /**
     * 发布一个领域事件
     *
     * @param customerId {@link CustomerId} 客户标识
     * @return {@link CustomerFullNameUpdated} 客户已注册的领域事件
     */
    public static CustomerFullNameUpdated issue(final CustomerId customerId, FullName fullName, final LocalDateTime registeredAt) {
        return new CustomerFullNameUpdated(EventId.generate(), customerId, fullName, registeredAt);
    }

    @Override
    public boolean sameEventAs(CustomerFullNameUpdated other) {
        // 通过判断事件标识是否相等来判断事件是否相等
        return Objects.equals(this.eventId, other.eventId);
    }
}

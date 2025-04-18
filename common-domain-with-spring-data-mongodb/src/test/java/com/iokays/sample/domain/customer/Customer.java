package com.iokays.sample.domain.customer;

import com.iokays.common.domain.mongodb.AbstractAggregateRoot;
import com.iokays.sample.domain.customer.command.RegisterCustomer;
import com.iokays.sample.domain.customer.event.CustomerRegistered;
import org.apache.commons.lang3.Validate;
import org.springframework.data.convert.ValueConverter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Unwrapped;

import java.time.LocalDateTime;

import static org.springframework.data.mongodb.core.mapping.Unwrapped.OnEmpty.USE_NULL;

@Document
public class Customer extends AbstractAggregateRoot<Customer> {

    @ValueConverter(CustomerIdConverter.class)
    private CustomerId customerId;

    @Unwrapped(onEmpty = USE_NULL)
    private EmailAddress emailAddress;

    private FullName fullName;

    private Gender gender;

    private LocalDateTime registeredAt;

    protected Customer() {
        super();
    }

    public Customer(FullName fullName, Gender gender, EmailAddress emailAddress) {
        this();
        this.customerId = CustomerId.makeCustomerId();
        this.registeredAt = LocalDateTime.now();

        this.fullName = Validate.notNull(fullName, "name must not be null");
        this.gender = Validate.notNull(gender, "gender must not be null");
        this.emailAddress = Validate.notNull(emailAddress, "emailAddress must not be null");

        this.registerEvent(CustomerRegistered.issue(this.customerId, this.registeredAt));
    }

    public static Customer registerBy(final RegisterCustomer cmd) {
        Validate.notNull(cmd, "注册客户的命令不能为空");

        return new Customer(
                cmd.fullName(),
                cmd.gender(),
                cmd.emailAddress());
    }

    @Override
    public boolean sameIdentityAs(Customer other) {
        return this.customerId.equals(other.customerId);
    }


}

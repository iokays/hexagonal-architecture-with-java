package com.iokays.sample.customer.domain;

import com.iokays.common.domain.jpa.AbstractAggregateRoot;
import com.iokays.sample.customer.domain.command.RegisterCustomer;
import com.iokays.sample.customer.domain.event.CustomerRegistered;
import jakarta.persistence.*;
import org.apache.commons.lang3.Validate;

import java.time.LocalDateTime;
import java.util.Objects;


@Entity(name = "t_customer")
public class Customer extends AbstractAggregateRoot<Customer> {

    @AttributeOverride(name = "id", column = @Column(name = "customer_id"))
    private CustomerId customerId;

    @AttributeOverride(name = "value", column = @Column(name = "email_address"))
    private EmailAddress emailAddress;

    @Embedded
    @AttributeOverride(name = "firstName", column = @Column(name = "first_name"))
    @AttributeOverride(name = "lastName", column = @Column(name = "last_name"))
    private FullName fullName;

    @Enumerated(EnumType.STRING)
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

    public CustomerId customerId() {
        return customerId;
    }

    @Override
    public boolean sameIdentityAs(Customer other) {
        return Objects.equals(this.customerId, other.customerId);
    }

}
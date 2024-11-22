package com.iokays.sample.customer.application.service;

import com.iokays.common.core.lock.DistributedLock;
import com.iokays.sample.customer.domain.Customer;
import com.iokays.sample.customer.domain.CustomerId;
import com.iokays.sample.customer.domain.CustomerRepository;
import com.iokays.sample.customer.domain.EmailAddress;
import com.iokays.sample.customer.domain.command.RegisterCustomer;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomerApplicationService {

    private final CustomerRepository customers;

    public CustomerApplicationService(CustomerRepository customers) {
        this.customers = customers;
    }

    @Transactional
    @DistributedLock(value = "customer", key = "#cmd.emailAddress.value") //这里未实现
    public CustomerId registerCustomer(final RegisterCustomer cmd) {
        final EmailAddress emailAddress = cmd.emailAddress();

        Validate.isTrue(customers.findByEmailAddress(emailAddress).isEmpty(), String.format("给定的电子邮件地址: %s 已经存在", emailAddress));

        final Customer customer = Customer.registerBy(cmd);
        customers.save(customer);

        return customer.customerId();
    }

}

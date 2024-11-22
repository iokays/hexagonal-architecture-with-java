package com.iokays.sample.customer.application.service;

import com.iokays.sample.customer.domain.EmailAddress;
import com.iokays.sample.customer.domain.FullName;
import com.iokays.sample.customer.domain.Gender;
import com.iokays.sample.customer.domain.command.RegisterCustomer;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerApplicationServiceTest {

    @Resource
    private CustomerApplicationService service;

    @Test
    void registerCustomer() {

        final var cmd = RegisterCustomer.issue(FullName.from("孙", "悟空"), Gender.MALE, EmailAddress.from("pengyuanbing@iokays.com"));

        final var customerId = service.registerCustomer(cmd);
        System.out.println("customerId = " + customerId);

    }
}

package com.iokays.sample.application.service;

import com.iokays.sample.domain.customer.EmailAddress;
import com.iokays.sample.domain.customer.FullName;
import com.iokays.sample.domain.customer.Gender;
import com.iokays.sample.domain.customer.command.RegisterCustomer;
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

package com.iokays.sample.application.service;

import com.iokays.sample.domain.customer.CustomerRepository;
import com.iokays.sample.domain.customer.EmailAddress;
import com.iokays.sample.domain.customer.FullName;
import com.iokays.sample.domain.customer.Gender;
import com.iokays.sample.domain.customer.command.RegisterCustomer;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class CustomerTestApplicationServiceTest {

    @Resource
    private CustomerApplicationService applicationService;

    @Resource
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();
    }

    @Test
    void registerCustomer() {
        final var cmd = RegisterCustomer.issue(FullName.from("孙", "悟空"), Gender.MALE, EmailAddress.from("pengyuanbing@iokays.com"));
        applicationService.registerCustomer(cmd);
    }

}
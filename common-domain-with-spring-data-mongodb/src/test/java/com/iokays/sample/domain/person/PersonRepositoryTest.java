package com.iokays.sample.domain.person;

import com.google.common.collect.Lists;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class PersonRepositoryTest {

    @Resource
    private PersonRepository personRepository;

    @Resource
    private AccountRepository accountRepository;

    @BeforeEach
    public void setUp() {
        personRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @Test
    void test() {
        List<Account> accounts = accountRepository.saveAll(Lists.newArrayList(new Account(100F), new Account(101F)));
        personRepository.save(new Person(100, accounts));
    }

}

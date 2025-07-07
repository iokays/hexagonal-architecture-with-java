package com.iokays;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.integration.jdbc.channel.PostgresSubscribableChannel;
import org.springframework.stereotype.Component;

@Slf4j
@Order
@Component
@AllArgsConstructor
public class MyDomainEventSubscribeRunner implements CommandLineRunner {

    private final PostgresSubscribableChannel channel;

    @Override
    public void run(String... args) {
        log.info("Subscribing to channel");
        channel.subscribe(message -> {
            log.info("Received message: {}", message);
        });
    }
}

package com.iokays.common;

import com.iokays.common.core.event.EventId;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.integration.jdbc.channel.PostgresSubscribableChannel;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    private final ApplicationEventPublisher publisher;
    private final PostgresSubscribableChannel subscribableChannel;

    @Override
    public void run(String... args) throws Exception {
        subscribableChannel.subscribe(message -> System.out.println("Received: " + message.getPayload()));
        for (int i = 0; i < 100; i++) {
            Try.run(() -> Thread.sleep(100));
            publisher.publishEvent(new SampleDomainEvent(EventId.generate()));
        }
    }
}

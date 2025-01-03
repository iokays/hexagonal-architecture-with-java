package com.iokays.common.traceid;

import io.opentelemetry.api.trace.Span;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
public class SampleLoggerService {

    private static final Logger log = LoggerFactory.getLogger(SampleLoggerService.class);

    public void show() {
        log.info("threadId: {}, traceId: {}, spanId: {}", Thread.currentThread().getName(), Span.current().getSpanContext().getTraceId(), Span.current().getSpanContext().getSpanId());
        IntStream.range(0, 10).parallel().forEach(v -> {
            //不支持 请查看: https://github.com/open-telemetry/opentelemetry-java-instrumentation/issues/709
            log.info("forEach: threadId: {}, traceId: {}, spanId: {}", Thread.currentThread().getName(), Span.current().getSpanContext().getTraceId(), Span.current().getSpanContext().getSpanId());
        });

        log.info("spanContext: {}", Span.current().getSpanContext());
        log.info("span: {}", Span.current());

    }

}

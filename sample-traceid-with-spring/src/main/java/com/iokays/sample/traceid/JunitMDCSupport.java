package com.iokays.sample.traceid;

import org.junit.jupiter.api.BeforeEach;
import org.slf4j.MDC;

import java.util.UUID;

public interface JunitMDCSupport {

    @BeforeEach
    default void setMDC() {
        MDC.put(TRACE.ID, UUID.randomUUID().toString());
    }


}

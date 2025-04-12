package com.iokays.common.core.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationServiceUnKnowException extends ApplicationServiceException {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationServiceUnKnowException.class);

    public ApplicationServiceUnKnowException(String message) {
        super(message);
    }

    public ApplicationServiceUnKnowException(String message, Throwable e) {
        super(message);
        logger.info(message, e);
    }
}

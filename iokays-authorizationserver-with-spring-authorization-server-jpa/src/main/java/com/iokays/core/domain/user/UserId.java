package com.iokays.core.domain.user;

import com.iokays.common.domain.jpa.AbstractId;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Slf4j
public class UserId extends AbstractId {

    protected UserId() {
        super();
    }

    public UserId(String id) {
        super(id);
    }

    public static UserId makeUserId() {
        String id = UUID.randomUUID().toString().replace("-", EMPTY);
        log.info("makeUserId: {}", id);
        return new UserId(id);
    }

}

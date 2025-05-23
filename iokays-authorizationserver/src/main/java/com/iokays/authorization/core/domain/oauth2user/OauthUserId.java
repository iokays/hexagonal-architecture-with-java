package com.iokays.authorization.core.domain.oauth2user;

import com.iokays.common.domain.jpa.AbstractId;
import jakarta.persistence.Embeddable;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Slf4j
@Embeddable
public class OauthUserId extends AbstractId {

    protected OauthUserId() {
        super();
    }

    public OauthUserId(String id) {
        super(id);
    }

    public static OauthUserId makeUserId() {
        String id = UUID.randomUUID().toString().replace("-", EMPTY);
        log.info("makeOauth2UserId: {}", id);
        return new OauthUserId(id);
    }

}

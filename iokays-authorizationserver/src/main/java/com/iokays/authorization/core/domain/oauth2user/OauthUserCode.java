package com.iokays.authorization.core.domain.oauth2user;

import com.iokays.common.domain.jpa.AbstractCode;
import jakarta.persistence.Embeddable;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Slf4j
@Embeddable
public class OauthUserCode extends AbstractCode {

    protected OauthUserCode() {
        super();
    }

    public OauthUserCode(String id) {
        super(id);
    }

    public static OauthUserCode makeUserId() {
        String id = UUID.randomUUID().toString().replace("-", EMPTY);
        log.info("makeOauth2UserId: {}", id);
        return new OauthUserCode(id);
    }

}

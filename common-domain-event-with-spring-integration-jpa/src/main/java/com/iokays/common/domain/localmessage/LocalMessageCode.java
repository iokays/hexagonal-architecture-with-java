package com.iokays.common.domain.localmessage;

import com.iokays.common.domain.jpa.AbstractCode;
import jakarta.persistence.Embeddable;


@Embeddable
public class LocalMessageCode extends AbstractCode {

    protected LocalMessageCode() {
        super();
    }

    public LocalMessageCode(String id) {
        super(id);
    }
}

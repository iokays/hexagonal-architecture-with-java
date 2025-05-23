package com.iokays.common.domain.localmessage;

import com.iokays.common.domain.jpa.AbstractId;
import jakarta.persistence.Embeddable;


@Embeddable
public class LocalMessageId extends AbstractId {

    protected LocalMessageId() {
        super();
    }

    public LocalMessageId(String id) {
        super(id);
    }
}

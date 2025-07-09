package com.iokays.common.core.domain.specification;

import com.iokays.common.core.domain.enums.FieldType;
import com.iokays.common.domain.jpa.IdentifiedValueObject;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import org.apache.commons.lang3.Validate;

import java.util.Objects;

@Entity
@Table(name="spec_field_mapping")
public class FieldMapping extends IdentifiedValueObject<FieldMapping> {

    private String name;

    @Enumerated(EnumType.STRING)
    private FieldType type;

    private String displayName;

    protected FieldMapping() {
        super();
    }

    public FieldMapping(String name, FieldType type, String displayName) {
        this.name = Validate.notBlank(name);
        this.type = Objects.requireNonNull(type);
        this.displayName = Validate.notBlank(displayName);
    }

    public String name() {
        return this.name;
    }



}

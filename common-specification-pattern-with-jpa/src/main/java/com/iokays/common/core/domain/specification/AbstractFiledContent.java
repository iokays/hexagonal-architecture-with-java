package com.iokays.common.core.domain.specification;


import com.iokays.common.domain.jpa.IdentifiedValueObject;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity(name = "spec_field_content")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractFiledContent<T> extends IdentifiedValueObject<T> {

    protected AbstractFiledContent() {
        super();
    }

    public abstract <E extends Comparable<Object>> E value();
    public abstract <E extends Comparable<Object>> E anotherValue();

}

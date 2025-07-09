package com.iokays.common.core.domain.specification;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("INTEGER")
@Table(name = "spec_integer_field_content")
public class IntegerFieldContent extends AbstractFiledContent<IntegerFieldContent> {

    private Integer value;
    private Integer anotherValue;

    protected IntegerFieldContent() {
        super();
    }

    public IntegerFieldContent(Integer value, Integer anotherValue) {
        this();
        this.value = value;
        this.anotherValue = anotherValue;
    }

    @Override
    public Integer value() {
        return this.value;
    }

    @Override
    public Integer anotherValue() {
        return this.anotherValue;
    }
}

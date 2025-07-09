package com.iokays.common.core.domain.specification;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("TEXT")
@Table(name = "spec_text_field_content")
public class TextFieldContent extends AbstractFiledContent<TextFieldContent> {

    private String value;
    private String anotherValue;


    protected TextFieldContent() {
        super();
    }

    public TextFieldContent(String value, String anotherValue) {
        this();
        this.value = value;
        this.anotherValue = anotherValue;
    }

    @Override
    public String value() {
        return this.value;
    }

    @Override
    public String anotherValue() {
        return this.anotherValue;
    }
}

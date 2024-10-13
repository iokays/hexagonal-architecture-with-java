package com.iokays.common.jpa;

import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;
import org.apache.commons.lang3.Validate;

import java.io.Serial;
import java.io.Serializable;

/**
 * 业务主键
 */
@Embeddable
@MappedSuperclass
public abstract class AbstractId implements Identity, Comparable<AbstractId>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    protected AbstractId(String anId) {
        this();
        this.setId(anId);
    }

    protected AbstractId() {
        super();
    }

    @Override
    public String id() {
        return this.id;
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }

        if (anObject instanceof AbstractId other) {
            return this.id().equals(other.id());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return +(this.hashOddValue() * this.hashPrimeValue()) + this.id().hashCode();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [id=" + id + "]";
    }

    protected int hashOddValue() {
        return this.getClass().hashCode();
    }

    protected int hashPrimeValue() {
        return 263;
    }

    protected void validateId(String anId) {
        // implemented by subclasses for validation.
        // throws a runtime exception if invalid.
    }

    private void setId(String anId) {
        Validate.notNull(anId, "The basic identity is required.");
        this.validateId(anId);
        this.id = anId;
    }

    @Override
    public int compareTo(AbstractId o) {
        return this.id().compareTo(o.id());
    }

}
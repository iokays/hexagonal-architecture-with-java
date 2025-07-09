package com.iokays.common.core.domain.specification;


import com.iokays.common.core.domain.enums.SpecificationOperator;
import com.iokays.common.core.domain.enums.SpecificationType;
import com.iokays.common.core.specification.Specification;
import com.iokays.common.domain.jpa.IdentifiedValueObject;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

@Entity
@Table(name="spec_specification")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractSpecification<T> extends IdentifiedValueObject<T> implements Predicate<Object> {

    private Integer rank;

    @Enumerated(EnumType.STRING)
    private SpecificationType type;

    @Enumerated(EnumType.STRING)
    private SpecificationOperator operator;

    @ManyToOne(fetch = FetchType.LAZY)
    private AbstractSpecification<?> parent;

    @OrderBy("rank")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "parent")
    private List<AbstractSpecification<?>> children;

    protected void addChild(AbstractSpecification<?> child) {
        child.setParent(this);
        children.add(child);
    }

    private void setParent(AbstractSpecification<?> parent) {
        this.parent = Objects.requireNonNull(parent);
    }

    protected AbstractSpecification() {
        super();
    }

    protected AbstractSpecification(Integer rank, SpecificationType type, SpecificationOperator operator) {
        this();
        this.rank = rank;
        this.type = type;
        this.operator = operator;
    }

}

package com.iokays.common.core.domain.specification;


import com.iokays.common.core.domain.enums.FieldOperator;
import com.iokays.common.core.domain.enums.FieldType;
import com.iokays.common.core.domain.enums.SpecificationOperator;
import com.iokays.common.core.domain.enums.SpecificationType;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import org.hibernate.annotations.*;
import org.mvel2.MVEL;

@Entity
@Table(name="spec_field_specification")
public class FieldSpecification extends AbstractSpecification<FieldSpecification> {

    @ManyToOne(cascade = {CascadeType.ALL})
    private FieldMapping fieldMapping;

    private FieldOperator fieldOperator;

    @ManyToOne(cascade = CascadeType.ALL)
    private AbstractFiledContent<?> expectedContent;

    protected FieldSpecification() {
        super();
    }

    public FieldSpecification(
            FieldMapping fieldMapping,
            FieldOperator fieldOperator,
            AbstractFiledContent<?> expectedContent,
            Integer rank,
            SpecificationType type,
            SpecificationOperator operator) {
        super(rank, type, operator);
        this.fieldMapping = fieldMapping;
        this.fieldOperator = fieldOperator;
        this.expectedContent = expectedContent;
    }

    @Override
    public boolean test(Object o) {
        final Object actualValue = MVEL.eval(fieldMapping.name(), o);
        final Comparable<Object> expectedValue = expectedContent.value();
        final Comparable<Object> expectedAnotherValue = expectedContent.anotherValue();
        if (actualValue instanceof Comparable<?> comparable) {
            boolean result;
            switch (fieldOperator) {
                case EQUALS:
                    result = expectedValue.compareTo(comparable) == 0;
                    break;
                case GREATER_THAN:
                    result = expectedValue.compareTo(comparable) < 0;
                    break;
                case LESS_THAN:
                    result = expectedValue.compareTo(comparable) > 0;
                    break;
                case BETWEEN:
                    result = expectedValue.compareTo(comparable) <= 0 &&
                            expectedAnotherValue.compareTo(comparable) >= 0;
                    break;
                default: throw new IllegalStateException("Field operator not supported");
            }
            return result;
        }

        throw new IllegalStateException("actualValue type not supported");
    }
}

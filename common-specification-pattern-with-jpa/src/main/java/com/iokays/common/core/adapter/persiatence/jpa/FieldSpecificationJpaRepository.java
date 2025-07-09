package com.iokays.common.core.adapter.persiatence.jpa;

import com.iokays.common.core.domain.specification.FieldSpecification;
import com.iokays.common.core.domain.specification.FieldSpecificationRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldSpecificationJpaRepository extends FieldSpecificationRepository, JpaRepository<FieldSpecification, Long> {
}

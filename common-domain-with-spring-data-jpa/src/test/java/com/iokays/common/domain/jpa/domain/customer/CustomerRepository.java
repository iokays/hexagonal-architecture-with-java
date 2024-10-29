package com.iokays.common.domain.jpa.domain.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 客户仓储, 与客户相关的数据访问接口
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmailAddress(final EmailAddress emailAddress);

}

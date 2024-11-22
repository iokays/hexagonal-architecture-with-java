package com.iokays.sample.customer.adapter.persistence.jpa;

import com.iokays.sample.customer.domain.Customer;
import com.iokays.sample.customer.domain.CustomerRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJpaRepository extends CustomerRepository, JpaRepository<Customer, Long> {
}

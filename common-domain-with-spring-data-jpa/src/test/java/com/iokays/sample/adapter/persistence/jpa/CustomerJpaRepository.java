package com.iokays.sample.adapter.persistence.jpa;

import com.iokays.sample.domain.customer.Customer;
import com.iokays.sample.domain.customer.CustomerRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJpaRepository extends CustomerRepository, JpaRepository<Customer, Long> {
}

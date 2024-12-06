package com.iokays.core.adapter.persistence.jpa;

import com.iokays.common.core.adapter.DrivenAdapter;
import com.iokays.core.domain.user.User;
import com.iokays.core.domain.user.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

@DrivenAdapter
public interface UserJpaRepository extends JpaRepository<User, Long>, UserRepository {
}

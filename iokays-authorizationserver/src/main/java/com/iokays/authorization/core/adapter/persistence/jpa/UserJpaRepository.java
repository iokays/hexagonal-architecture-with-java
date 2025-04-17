package com.iokays.authorization.core.adapter.persistence.jpa;

import com.iokays.authorization.core.domain.user.User;
import com.iokays.authorization.core.domain.user.UserRepository;
import com.iokays.common.core.adapter.DrivenAdapter;
import org.springframework.data.jpa.repository.JpaRepository;

@DrivenAdapter
public interface UserJpaRepository extends JpaRepository<User, Long>, UserRepository {
}

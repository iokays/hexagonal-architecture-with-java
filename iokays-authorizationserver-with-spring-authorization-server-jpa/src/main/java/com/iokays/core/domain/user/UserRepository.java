package com.iokays.core.domain.user;

import com.iokays.common.core.infrastructure.Repository;

public interface UserRepository extends Repository {

    User save(User user);

    User findByUsername(String username);

}

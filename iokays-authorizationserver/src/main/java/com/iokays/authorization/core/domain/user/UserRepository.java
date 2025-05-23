package com.iokays.authorization.core.domain.user;

import com.iokays.common.core.infrastructure.Repository;

public interface UserRepository extends Repository {

    User save(User user);

    User findByUsername(Username username);

}

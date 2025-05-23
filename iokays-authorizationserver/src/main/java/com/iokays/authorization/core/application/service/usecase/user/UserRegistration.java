package com.iokays.authorization.core.application.service.usecase.user;

import com.iokays.authorization.core.domain.user.Username;
import com.iokays.authorization.core.domain.user.command.RegisterUser;

public interface UserRegistration {

    Username registerUser(RegisterUser cmd);

}

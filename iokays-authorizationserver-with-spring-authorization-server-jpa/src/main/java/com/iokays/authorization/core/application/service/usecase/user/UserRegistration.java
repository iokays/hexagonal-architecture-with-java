package com.iokays.authorization.core.application.service.usecase.user;

import com.iokays.authorization.core.domain.user.UserId;
import com.iokays.authorization.core.domain.user.command.RegisterUser;

public interface UserRegistration {

    UserId registerUser(RegisterUser cmd);

}

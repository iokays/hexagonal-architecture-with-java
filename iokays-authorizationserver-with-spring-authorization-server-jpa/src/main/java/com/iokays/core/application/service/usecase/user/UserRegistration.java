package com.iokays.core.application.service.usecase.user;

import com.iokays.core.domain.user.UserId;
import com.iokays.core.domain.user.command.RegisterUser;

public interface UserRegistration {

    UserId registerUser(RegisterUser cmd);

}

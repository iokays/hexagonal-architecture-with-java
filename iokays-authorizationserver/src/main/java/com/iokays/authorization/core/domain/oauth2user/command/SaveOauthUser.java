package com.iokays.authorization.core.domain.oauth2user.command;

import com.iokays.authorization.core.domain.clientregistration.RegistrationCode;
import com.iokays.common.core.command.Command;
import com.iokays.common.core.command.CommandId;
import lombok.Builder;

/**
 * Oauth2User: 设置为单独一个领域层，是为了简化 user层。 可能系统没有自己的用户体系。
 * 如果系统同时支持表单和Oauth2登录， 并且表单用户为系统的核心用户模块，Oauth2作为登录授权的一种方式，将该部分放到user领域层，并建立一对多的关系。
 */
@Builder
public record SaveOauthUser(CommandId id, RegistrationCode registrationId,
                            String subject, String name, String givenName, String familyName, String middleName,
                            String nickname, String preferredUsername, String profile, String picture, String website,
                            String email, Boolean emailVerified, String gender, String birthdate, String zoneinfo,
                            String locale, String phoneNumber, Boolean phoneNumberVerified, String address,
                            String updatedAt, String claim) implements Command {
}

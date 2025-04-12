package com.iokays.authorization.core.application.service;

import com.iokays.authorization.core.domain.clientregistration.RegistrationId;
import com.iokays.authorization.core.domain.oauth2user.OauthUser;
import com.iokays.authorization.core.domain.oauth2user.OauthUserInfo;
import com.iokays.authorization.core.domain.oauth2user.OauthUserRepository;
import com.iokays.authorization.core.domain.oauth2user.command.SaveOauthUser;
import com.iokays.common.core.service.ApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class OauthUserApplicationService implements ApplicationService {

    private final OauthUserRepository oauthUserRepository;

    @CacheEvict(value = "OauthUserInfoBySubjectAndClientRegistrationId", key = "#command.subject + '_' + #command.clientRegistrationId")
    public void save(SaveOauthUser command) {
        log.debug("command: {}", command);
        oauthUserRepository.findBySubjectAndRegistrationId(command.subject(), command.registrationId())
                .ifPresentOrElse(
                        v -> v.update(command),
                        () -> oauthUserRepository.save(new OauthUser(command))
                );
    }

    @Cacheable(value = "OauthUserInfoBySubjectAndClientRegistrationId", key = "#subject + '_' + #clientRegistrationId")
    public OauthUserInfo findBySubjectAndClientRegistrationId(String subject, RegistrationId registrationId) {
        return oauthUserRepository
                .findBySubjectAndRegistrationId(subject, registrationId)
                .map(OauthUser::info)
                .orElseThrow(() -> new IllegalArgumentException("not found %s".formatted(subject)));
    }


}

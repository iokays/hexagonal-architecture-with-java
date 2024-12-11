package com.iokays.core.application.service;

import com.iokays.core.domain.clientregistration.ClientRegistrationId;
import com.iokays.core.domain.oauth2user.OauthUser;
import com.iokays.core.domain.oauth2user.OauthUserInfo;
import com.iokays.core.domain.oauth2user.OauthUserRepository;
import com.iokays.core.domain.oauth2user.command.SaveOauthUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class OauthUserApplicationService {

    private final OauthUserRepository oauthUserRepository;

    public void save(SaveOauthUser command) {
        log.debug("command: {}", command);
        oauthUserRepository.findBySubjectAndClientRegistrationId(command.subject(), command.clientRegistrationId())
                .ifPresentOrElse(
                        v -> v.update(command),
                        () -> oauthUserRepository.save(new OauthUser(command))
                );
    }

    public OauthUserInfo findBySubjectAndClientRegistrationId(String subject, ClientRegistrationId clientRegistrationId) {
        return oauthUserRepository
                .findBySubjectAndClientRegistrationId(subject, clientRegistrationId)
                .map(OauthUser::info)
                .orElseThrow(() -> new IllegalArgumentException("not found %s".formatted(subject)));
    }


}

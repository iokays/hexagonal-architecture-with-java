package com.iokays.authorization.core.application.service;

import com.iokays.authorization.core.adapter.persistence.jpa.OauthUserJpaRepository;
import com.iokays.authorization.core.adapter.persistence.querydsl.OauthUserDao;
import com.iokays.authorization.core.domain.oauth2user.OauthUser;
import com.iokays.authorization.core.domain.oauth2user.OauthUserInfo;
import com.iokays.authorization.core.utils.Pages;
import com.iokays.common.core.service.ApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class OauthUserQueryApplicationService implements ApplicationService {

    private final OauthUserDao oauthUserDao;
    private final OauthUserJpaRepository oauthUserJpaRepository;

    public Page<OauthUserInfo> page(Pageable pageable) {
        // final var result = oauthUserDao.page(pageable);
        return Pages.toNewPage(pageable,
                oauthUserJpaRepository.findAll(pageable),
                OauthUser::info
        );
    }

}

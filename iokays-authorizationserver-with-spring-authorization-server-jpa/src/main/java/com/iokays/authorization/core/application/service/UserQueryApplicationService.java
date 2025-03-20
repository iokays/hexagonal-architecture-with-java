package com.iokays.authorization.core.application.service;

import com.iokays.authorization.core.adapter.persistence.querydsl.UserDao;
import com.iokays.authorization.core.adapter.persistence.querydsl.table.TUsers;
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
public class UserQueryApplicationService implements ApplicationService {

    private final UserDao userDao;

    public Page<TUsers> page(Pageable pageable) {
        return userDao.page(pageable);
    }

}

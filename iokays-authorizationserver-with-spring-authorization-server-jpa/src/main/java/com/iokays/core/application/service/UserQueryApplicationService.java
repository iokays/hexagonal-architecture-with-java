package com.iokays.core.application.service;

import com.iokays.common.core.service.ApplicationService;
import com.iokays.core.application.service.table.QTUsers;
import com.querydsl.core.Tuple;
import com.querydsl.sql.SQLQueryFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class UserQueryApplicationService implements ApplicationService {

    private final SQLQueryFactory sqlQueryFactory;

    public void query() {
        final var tUser = QTUsers.tUsers;

        List<Tuple> result = sqlQueryFactory
                .select(
                        tUser.userId,
                        tUser.username,
                        tUser.password,
                        tUser.enabled,
                        tUser.concurrencyVersion,
                        tUser.createdDate,
                        tUser.lastModifiedDate
                )
                .from(tUser)
                .fetch();

        log.info("result: {}", result);
    }

}

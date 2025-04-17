package com.iokays.authorization.core.adapter.persistence.querydsl;

import com.iokays.authorization.core.adapter.persistence.querydsl.table.QTOauthUsers;
import com.iokays.authorization.core.adapter.persistence.querydsl.table.TOauthUsers;
import com.querydsl.core.types.Projections;
import com.querydsl.sql.SQLQueryFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class OauthUserDao {

    private final SQLQueryFactory sqlQueryFactory;

    public Page<TOauthUsers> page(Pageable pageable) {
        final var t = QTOauthUsers.tOauthUsers;

        //总数
        final var total = sqlQueryFactory.from(t).fetchCount();

        //列表
        final var q = sqlQueryFactory
                .select(Projections.bean(TOauthUsers.class, t.all()))
                .from(t);

        if (pageable.isPaged()) {
            q.offset(pageable.getOffset()).limit(pageable.getPageSize());
        }
        final var result = q.fetch();

        log.info("result: {}", result);
        return new PageImpl<>(result, pageable, total);
    }

}

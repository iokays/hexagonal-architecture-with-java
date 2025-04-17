package com.iokays.authorization.core.adapter.persistence.querydsl;

import com.iokays.authorization.core.adapter.persistence.querydsl.table.QTUsers;
import com.iokays.authorization.core.adapter.persistence.querydsl.table.TUsers;
import com.querydsl.core.types.Projections;
import com.querydsl.sql.SQLQueryFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * 该部分数据的应该是备份的准实时数据库，可能不是源数据源，
 * 这里只是一种实现方式，当我们只有一份数据源时且为简单的查询，可以直接使用领域层的资源库.
 * 当资源库使用的是领域对象，就需要对领域层的数据进行转换
 */
@Slf4j
@Component
@AllArgsConstructor
public class UserDao {

    private final SQLQueryFactory sqlQueryFactory;

    public Page<TUsers> page(Pageable pageable) {
        final var tUser = QTUsers.tUsers;

        //总数
        final var total = sqlQueryFactory.from(tUser).fetchCount();

        //列表
        final var q = sqlQueryFactory
                .select(Projections.bean(TUsers.class,
                        tUser.userId,
                        tUser.username,
                        tUser.enabled,
                        tUser.concurrencyVersion,
                        tUser.createdDate,
                        tUser.lastModifiedDate))
                .from(tUser);

        if (pageable.isPaged()) {
            q.offset(pageable.getOffset()).limit(pageable.getPageSize());
        }
        final var result = q.fetch();

        log.info("result: {}", result);
        return new PageImpl<>(result, pageable, total);
    }

}

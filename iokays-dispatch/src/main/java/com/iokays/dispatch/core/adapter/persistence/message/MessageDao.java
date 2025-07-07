package com.iokays.dispatch.core.adapter.persistence.message;

import com.iokays.dispatch.core.adapter.persistence.message.table.ChannelMessage;
import com.iokays.dispatch.core.adapter.persistence.message.table.QChannelMessage;
import com.querydsl.sql.SQLQueryFactory;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageDao {

    private final SQLQueryFactory localMessageSQLQueryFactory;

    public MessageDao(
            @Qualifier("localMessageSQLQueryFactory") SQLQueryFactory localMessageSQLQueryFactory) {
        this.localMessageSQLQueryFactory = localMessageSQLQueryFactory;
    }

    private Tuple2<SQLQueryFactory, String> target(final String category) {
        if (StringUtils.equals("users", category)) {
            return Tuple.of(localMessageSQLQueryFactory, "int_channel_message");
        }
        if (StringUtils.equals("other", category)) {
            return Tuple.of(localMessageSQLQueryFactory, "int_channel_message");
        }
        return null;
    }

    public Page<ChannelMessage> page(String category, Pageable pageable) {
        final var target = this.target(category);
        if (null == target) {
            return Page.empty();
        }

        final SQLQueryFactory sqlQueryFactory = target._1();
        final String tableName = target._2();

        final var message = new QChannelMessage(tableName, "PUBLIC", tableName);

        //总数
        final var total = sqlQueryFactory.from(message).fetchCount();

        //列表
        final var q = sqlQueryFactory
                .select(message)
                .from(message)
//                .where(message.status.eq(1))
                ;

        if (pageable.isPaged()) {
            q.offset(pageable.getOffset()).limit(pageable.getPageSize());
        }
        final var result = q.fetch();

        log.info("result: {}", result);

        return new PageImpl<>(result, pageable, total);
    }

}

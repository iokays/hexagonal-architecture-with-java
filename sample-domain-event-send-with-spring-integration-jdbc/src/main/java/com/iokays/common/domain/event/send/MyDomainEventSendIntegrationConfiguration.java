package com.iokays.common.domain.event.send;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.jdbc.JdbcPollingChannelAdapter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * 基于数据库的入站适配器
 * 读取数据库中的事件，并且更新数据库
 */
@Configuration
@EnableIntegration
public class MyDomainEventSendIntegrationConfiguration {

    //logger
    private static final Logger log = LoggerFactory.getLogger(MyDomainEventSendIntegrationConfiguration.class);

    /**
     * 从数据库中读取未发送的事件，标记为已发送【1】
     *
     * @param dataSource
     * @return
     */
    @Bean
    public MessageSource<Object> jdbcMessageSource(DataSource dataSource) {
        final var messageSource = new JdbcPollingChannelAdapter(dataSource, "SELECT * FROM LOCAL_MESSAGE WHERE MESSAGE_STATE = 0 ORDER BY MESSAGE_SEQUENCE LIMIT 1024");
        messageSource.setUpdateSql("UPDATE LOCAL_MESSAGE SET MESSAGE_STATE = 1 WHERE MESSAGE_ID in (:ID)");
        messageSource.setUpdateSqlParameterSourceFactory(v -> {
            final List<Map<String, Object>> list = (List<Map<String, Object>>) v;
            //获取查询出来的全部 MESSAGE_ID，并更新为 1
            final var ids = list.stream().map(v1 -> v1.get("MESSAGE_ID")).map(Object::toString).toList();
            return new MapSqlParameterSource().addValue("ID", ids);
        });
        return messageSource;
    }

    /**
     * 轮询读取数据库中的事件
     *
     * @param jdbcMessageSource
     * @return
     */
    @Bean
    public IntegrationFlow integrationFlow(final MessageSource<Object> jdbcMessageSource) {
        return IntegrationFlow
                .from(jdbcMessageSource, e -> e.poller(Pollers.fixedRate(100).transactional()))
                .handle(v -> {
                    //将这一步改为 发送到MQ, 改为MQ的出站通道适配器
                    log.info("message: {}", v);
                })
                .get();
    }

}


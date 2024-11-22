package com.iokays.sample;

import com.google.common.collect.Lists;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SampleJdbcService {

    private final JdbcTemplate jdbcTemplate;

    public SampleJdbcService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(final String table, final List<String> content, final int batchSize) {
        final var sql = "INSERT INTO " + table + " (CONTENT) VALUES (?)";
        jdbcTemplate.batchUpdate(sql, content, batchSize, (ps, c) -> ps.setString(1, c));
    }

    public void multiInsert(final String table, final List<String> content, final int batchSize) {
        final var sql = "INSERT INTO " + table + " (CONTENT) VALUES ";
        Lists.partition(content, batchSize).forEach(v -> {
            jdbcTemplate.update(sql + v.stream().map(v1 -> "('" + v1 + "')").collect(Collectors.joining(",")));
        });
    }

}

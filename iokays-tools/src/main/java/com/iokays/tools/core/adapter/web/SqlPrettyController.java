package com.iokays.tools.core.adapter.web;

import com.manticore.jsqlformatter.JSQLFormatter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@RequestMapping("/sqlPretty")
public class SqlPrettyController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping
    public String sqlPretty(@RequestBody RequestModel model) throws Exception {
        return JSQLFormatter.format(model.sql());
    }

    @Builder
    public record RequestModel(String sql) implements Serializable {};
}

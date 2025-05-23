package com.iokays.tools.core.adapter.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Builder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@RequestMapping("/jsonPretty")
public class JsonPrettyController {

    private final ObjectMapper objectMapper;

    public JsonPrettyController() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // 启用美化
    }

    @PostMapping
    public String jsonPretty(@RequestBody RequestModel model) {
        final var jsonStr = model.content();
        if (StringUtils.isEmpty(jsonStr)) {
            return "内容为空: %s".formatted(model);
        }

        try {
            // Parse the input JSON string to validate it
            Object jsonObject = objectMapper.readValue(jsonStr, Object.class);
            // Convert it back to pretty-printed JSON
            return objectMapper.writeValueAsString(jsonObject);
        } catch (JsonProcessingException e) {
            return "Invalid JSON: " + e.getMessage();
        }
    }


    @Builder
    public record RequestModel(String content) implements Serializable {
    }

}
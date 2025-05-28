package com.iokays.mcp.config.tools;

import com.iokays.mcp.core.adapter.service.RsaService;
import com.iokays.mcp.core.adapter.service.WeatherService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToolsConfig {

    @Bean
    public ToolCallbackProvider weatherTools(
            WeatherService weatherService,
            RsaService rsaService) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(weatherService, rsaService)
                .build();
    }
}

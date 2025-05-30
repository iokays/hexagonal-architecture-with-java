package com.iokays.mcp.config.tools;

import com.iokays.mcp.core.adapter.service.RsaMcpService;
import com.iokays.mcp.core.adapter.service.WeatherMcpService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToolsConfig {

    @Bean
    public ToolCallbackProvider weatherTools(
            WeatherMcpService weatherMcpService,
            RsaMcpService rsaMcpService) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(weatherMcpService, rsaMcpService)
                .build();
    }
}

package com.iokays.mcp.core.adapter.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class WeatherService {

    @Tool(description = "获取指定城市的天气信息")
    public String getWeather(String cityName) {
        if (Objects.equals(cityName, "北京")) {
            return "晴天☀️";
        }
        return null;
    }
}

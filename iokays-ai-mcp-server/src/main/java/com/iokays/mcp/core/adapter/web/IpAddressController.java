package com.iokays.mcp.core.adapter.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import java.util.Objects;

@RestController
@RequestMapping("/ipAddress")
public class IpAddressController {

    @GetMapping
    public String ipAddress(ServerWebExchange exchange) {
        final var request = exchange.getRequest();
        final var realIp = request.getHeaders().getFirst("X-Real-IP");
        if (StringUtils.isNotBlank(realIp)) {
            return realIp;
        }
        final var forwardedFor = request.getHeaders().getFirst("X-Forwarded-For");
        if (StringUtils.isNotBlank(forwardedFor)) {
            return forwardedFor.split(",")[0].trim();
        }
        final var remoteAddress = request.getRemoteAddress();
        if (Objects.isNull(remoteAddress) || Objects.isNull(remoteAddress.getAddress())) {
            return "未知IP";
        }
        return remoteAddress.getAddress().getHostAddress();
    }
}
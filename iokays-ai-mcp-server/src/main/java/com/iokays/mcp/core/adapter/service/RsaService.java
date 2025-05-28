package com.iokays.mcp.core.adapter.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iokays.mcp.core.application.service.KeyGeneratorApplicationService;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class RsaService {

    private final KeyGeneratorApplicationService keyGeneratorApplicationService;
    private final ObjectMapper objectMapper;

    @Tool(description = "生成指定长度的Rsa的公钥和私钥")
    public String rsaKeyGenerator(int size) {
        final KeyGeneratorApplicationService.ResponseModel rsa = keyGeneratorApplicationService.keyGenerator("rsa", size);
        return Try.of(() -> objectMapper.writeValueAsString(rsa))
                .onFailure(e -> log.error("错误: ", e)).getOrElse("");
    }
}

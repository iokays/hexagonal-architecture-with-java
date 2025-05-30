package com.iokays.mcp.core.adapter.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iokays.mcp.core.adapter.service.model.RsaResponseModel;
import com.iokays.mcp.core.application.service.KeyGeneratorApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Slf4j
@Service
@AllArgsConstructor
public class RsaMcpService {

    private final KeyGeneratorApplicationService keyGeneratorApplicationService;
    private final ObjectMapper objectMapper;

    @Tool(description = "生成指定长度的Rsa的公钥和私钥")
    public String rsaKeyGenerator(
            @ToolParam(description = "长度") int size) throws JsonProcessingException {
        log.info("rsaKeyGenerator size: {}", size);
        final KeyGeneratorApplicationService.ResponseModel rsa = keyGeneratorApplicationService.keyGenerator("rsa", size);

        final RsaResponseModel build = RsaResponseModel.builder()
                .keyId(rsa.keyId())
                .publicKey(Base64.getEncoder().encodeToString(rsa.publicKey()))
                .privateKey(Base64.getEncoder().encodeToString(rsa.privateKey()))
                .build();

        final String s = objectMapper.writeValueAsString(build);
        log.info("rsaKeyGenerator size: {}, result: {}", size, s);
        return s;

    }
}

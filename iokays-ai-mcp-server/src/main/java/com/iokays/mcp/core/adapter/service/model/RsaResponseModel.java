package com.iokays.mcp.core.adapter.service.model;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record RsaResponseModel(String keyId,
                               String publicKey,
                               String privateKey) implements Serializable {}

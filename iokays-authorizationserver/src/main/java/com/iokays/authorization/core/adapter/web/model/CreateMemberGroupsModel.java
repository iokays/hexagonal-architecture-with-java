package com.iokays.authorization.core.adapter.web.model;


import java.io.Serializable;
import java.util.List;

/**
 * 用于创建客户端注册信息的模型类。
 * 该类包含客户端在OAuth2或OpenID Connect协议中注册所需的各项信息。
 */

/**
 * 客户端注册模型记录类。
 * 实现了Serializable接口，支持序列化操作。
 */
public record CreateMemberGroupsModel(
        String username,
        List<String> groupIds
) implements Serializable {
}
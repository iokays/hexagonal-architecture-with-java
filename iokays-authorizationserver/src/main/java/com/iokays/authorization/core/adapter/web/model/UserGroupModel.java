package com.iokays.authorization.core.adapter.web.model;

import lombok.Builder;

import java.io.Serializable;

/**
 * UserPageModel 是一个不可变的数据类，用于表示用户页面的数据模型。
 * 该类自动生成 getter、equals、hashCode 和 toString 方法。
 * 实现了 Serializable 接口以支持序列化。
 */
@Builder
public record UserGroupModel(
        String groupId,
        String groupName,
        boolean authorized) implements Serializable {
}

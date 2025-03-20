package com.iokays.authorization.core.adapter.web.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * UserPageModel 是一个不可变的数据类，用于表示用户页面的数据模型。
 * 该类自动生成 getter、equals、hashCode 和 toString 方法。
 * 实现了 Serializable 接口以支持序列化。
 */
public record PageUserModel(
        /**
         * 并发版本号，用于乐观锁控制。
         */
        Integer concurrencyVersion,

        /**
         * 创建时间，表示记录的创建时间戳。
         */
        Timestamp createdDate,

        /**
         * 是否启用，表示用户是否处于启用状态。
         */
        Boolean enabled,

        /**
         * 最后修改时间，表示记录的最后修改时间戳。
         */
        Timestamp lastModifiedDate,

        /**
         * 用户 ID，表示用户的唯一标识符。
         */
        String userId,

        /**
         * 用户名，表示用户的登录名或显示名。
         */
        String username
) implements Serializable {
}

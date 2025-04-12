package com.iokays.authorization.core.adapter.web.model;

import java.io.Serializable;

/**
 * OAuth 用户查询模型类。
 * 用于封装与 OAuth 用户相关的查询信息。
 */
public record PageOauthUserModel(

        /**
         * OAuth 用户 ID。
         */
        String oauthUserId,

        /**
         * 客户端注册 ID。
         * 标识用户注册的客户端应用。
         */
        String registrationId,

        /**
         * 用户唯一标识符（Subject）。
         * 通常由 OAuth 提供者分配。
         */
        String subject,

        /**
         * 用户的全名。
         */
        String name,

        /**
         * 用户的名字。
         */
        String givenName,

        /**
         * 用户的姓氏。
         */
        String familyName,

        /**
         * 用户的中间名。
         */
        String middleName,

        /**
         * 用户的昵称。
         */
        String nickname,

        /**
         * 用户的首选用户名。
         */
        String preferredUsername,

        /**
         * 用户的个人主页 URL。
         */
        String profile,

        /**
         * 用户的头像 URL。
         */
        String picture,

        /**
         * 用户的个人网站 URL。
         */
        String website,

        /**
         * 用户的电子邮件地址。
         */
        String email,

        /**
         * 电子邮件地址是否已验证。
         */
        Boolean emailVerified,

        /**
         * 用户的性别。
         */
        String gender,

        /**
         * 用户的出生日期。
         */
        String birthdate,

        /**
         * 用户的时区信息。
         */
        String zoneinfo,

        /**
         * 用户的区域设置（语言和地区）。
         */
        String locale,

        /**
         * 用户的电话号码。
         */
        String phoneNumber,

        /**
         * 电话号码是否已验证。
         */
        Boolean phoneNumberVerified,

        /**
         * 用户的地址。
         */
        String address,

        /**
         * 用户信息最后更新时间。
         */
        String updatedAt,

        /**
         * 自定义声明字段。
         * 用于存储 OAuth 提供者返回的其他用户信息。
         */
        String claim
) implements Serializable {
}

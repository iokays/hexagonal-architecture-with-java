package com.iokays.core.domain.user;

import com.iokays.common.core.service.DomainService;

/**
 * 密码服务
 */
public interface PasswordService extends DomainService {

    /**
     * 加密
     *
     * @param rawPassword
     * @return
     */
    String encode(CharSequence rawPassword);

    /**
     * 比对
     *
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    boolean matches(CharSequence rawPassword, String encodedPassword);

}

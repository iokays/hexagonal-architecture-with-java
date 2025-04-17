package com.iokays.authorization.core.adapter.application.service;

import com.iokays.common.core.proxy.CurrentProxy;
import com.iokays.common.core.service.ApplicationService;
import org.springframework.aop.framework.AopContext;

@Deprecated
public interface SpringAopProxy<T extends ApplicationService> extends CurrentProxy<T> {

    @Override
    default T _this() {
        return (T) AopContext.currentProxy();
    }

}

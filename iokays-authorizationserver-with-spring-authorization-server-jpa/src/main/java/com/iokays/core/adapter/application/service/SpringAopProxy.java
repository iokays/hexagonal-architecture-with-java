package com.iokays.core.adapter.application.service;

import com.iokays.common.core.proxy.CurrentProxy;
import com.iokays.common.core.service.ApplicationService;
import org.springframework.aop.framework.AopContext;

@Deprecated
public class SpringAopProxy<T extends ApplicationService> implements CurrentProxy<T> {
    @Override
    public T _this() {
        return (T) AopContext.currentProxy();
    }
}

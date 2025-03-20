package com.iokays.authorization.core.domain.user;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class DomainRegistry implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static PasswordService passwordService() {
        return applicationContext.getBean(PasswordService.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext anApplicationContext) throws BeansException {
        applicationContext = anApplicationContext;
    }

}

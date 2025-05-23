package com.iokays.authorization.core.domain;

import com.iokays.authorization.core.domain.user.GroupDomainService;
import com.iokays.authorization.core.domain.user.PasswordService;
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

    public static GroupDomainService groupDomainService() {
        return applicationContext.getBean(GroupDomainService.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext anApplicationContext) throws BeansException {
        applicationContext = anApplicationContext;
    }

}

package com.iokays.authorization.core.utils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;

@Slf4j
@Component
@AllArgsConstructor
public class Validations implements ApplicationContextAware {

    private static Validator validator;

    public static void validateObject(Object bean) {
        final var errors = validator.validateObject(bean);

        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> log.error(error.getDefaultMessage()));
            throw new RuntimeException("数据 校验失败");
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        validator = applicationContext.getBean(Validator.class);
    }

}
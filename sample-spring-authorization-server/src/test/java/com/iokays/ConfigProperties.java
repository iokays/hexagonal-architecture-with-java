package com.iokays;// 使用Properties类来读取properties文件

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 不建议这样使用
 * 只是在：在不使用spring 的情况下，读取配置文件.
 */
@Slf4j
public class ConfigProperties {

    public static void main(String[] args) {
        System.out.println(value("username"));
    }

    public static String value(final String key) {
        final Properties properties = new Properties();
        try (InputStream inputStream = ConfigProperties.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            log.error("error file", e);
            throw new RuntimeException("error file");
        }
        // 获取properties文件中的值
        final String value = properties.getProperty(key);

        log.info("{}:{}", key, value);

        return value;
    }
}
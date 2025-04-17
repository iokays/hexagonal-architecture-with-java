package com.iokays;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.Serializable;

@Getter
@Configuration
@PropertySource("classpath:custom.properties")
public class CustomProperties implements Serializable {

    @Value("${google.clientId}")
    private String googleClientId;

    @Value("${google.clientSecret}")
    private String googleClientSecret;

    @Value("${workWinXin.clientId}")
    private String workWinXinClientId;

    @Value("${workWinXin.clientSecret}")
    private String workWinXinClientSecret;

}

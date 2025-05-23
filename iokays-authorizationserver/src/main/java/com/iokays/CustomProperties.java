package com.iokays;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Getter
@Configuration
public class CustomProperties implements Serializable {

    @Value("${account.google.clientId}")
    private String googleClientId;

    @Value("${account.google.clientSecret}")
    private String googleClientSecret;

    @Value("${account.workWinXin.clientId}")
    private String workWinXinClientId;

    @Value("${account.workWinXin.clientSecret}")
    private String workWinXinClientSecret;

}

package com.iokays;

import java.util.Base64;


public class IdTokenDecoder {

    /**
     * <a href="https://jwt.io/">校验jwt</a>
     *
     * @param args
     */
    public static void main(String[] args) {
        final var idToken = ConfigProperties.value("id_token");

        String[] parts = idToken.split("\\.");

        if (parts.length == 3) {
            String header = new String(Base64.getUrlDecoder().decode(parts[0]));
            String payload = new String(Base64.getUrlDecoder().decode(parts[1]));

            System.out.println("Header: " + header);

            /**
             * {
             *   "sub": "user", // 主体标识符，通常是用户ID
             *   "aud": "login-client", // 接收令牌的受众，通常是客户端ID
             *   "azp": "login-client", // 授权方，通常是客户端ID
             *   "auth_time": 1731390028, // 认证时间，Unix时间戳
             *   "iss": "http://localhost:8080", // 发行者，通常是授权服务器的URL
             *   "exp": 1731393365, // 过期时间，Unix时间戳
             *   "iat": 1731391565, // 签发时间，Unix时间戳
             *   "jti": "60e4105a-efff-4258-9c85-b5e28a0772bd", // 令牌唯一标识符
             *   "sid": "NQCryYJRihZRpxRUFuB7gZx8tOggii0zbSOVunJw_HI" // 会话ID
             * }
             */
            System.out.println("Payload: " + payload);


        } else {
            System.out.println("Invalid JWT format.");
        }
    }
}

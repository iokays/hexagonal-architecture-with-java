//package com.iokays;
//
//import com.iokays.common.core.adapter.DrivenAdapter;
//import com.iokays.security.client.application.service.ClientRegistrationApplicationService;
//import com.iokays.security.client.domain.event.ClientRegistrationCreated;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.security.oauth2.core.AuthorizationGrantType;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.event.TransactionalEventListener;
//
//import java.util.HashMap;
//import java.util.Map;
//
/// **
// * 监听业务事件, 修改默认登录页的Oauth2登录选项， 但是这种方式在分布式环境，需要所有服务都需要监听，来实现全量的更新，
// * 可以实现 Map<String, String> loginUrlToClientName，当作一个Bean的形式注入。详见：MySecurityClientConfig
// *
// */
//
//@Slf4j
//@Component
//@DrivenAdapter
//@AllArgsConstructor
//public class MySecurityClientListener {
//
//    private final ClientRegistrationApplicationService clientRegistrationApplicationService;
//    private final SecurityFilterChain securityFilterChain;
//
//    /**
//     * 监听到 ClientRegistrationCreated 消息， 修改
//     * @param evt
//     */
//    @Async
//    @TransactionalEventListener
//    public void handle(final ClientRegistrationCreated evt) {
//        log.info("监听到 ClientRegistrationCreated 消息");
//
//        for (var filter : securityFilterChain.getFilters()) {
//            if (filter instanceof DefaultLoginPageGeneratingFilter targetFile) {
//                Map<String, String> loginUrlToClientName = new HashMap<>();
//                clientRegistrationApplicationService.iterator().forEachRemaining((registration) -> {
//                    if (AuthorizationGrantType.AUTHORIZATION_CODE.equals(registration.getAuthorizationGrantType())) {
//                        String authorizationRequestUri = "/oauth2/authorization/" + registration.getRegistrationId();
//                        loginUrlToClientName.put(authorizationRequestUri, registration.getClientName());
//                    }
//                });
//
//                log.info("更新默认登录页的Oauth2登录选项: {}", loginUrlToClientName);
//                targetFile.setOauth2AuthenticationUrlToClientName(loginUrlToClientName);
//            }
//        }
//    }
//
//}

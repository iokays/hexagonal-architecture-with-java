//package com.iokays.authorization.core.adapter.web;
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebSession;
//import reactor.core.publisher.Mono;
//
//@RestController
//@RequestMapping("session")
//public class SessionController {
//
//    @RequestMapping("id")
//    public Mono<String> id(ServerWebExchange exchange) {
//        return exchange.getSession().map(WebSession::getId);
//    }
//
//    @RequestMapping("force")
//    public Mono<String> force(ServerWebExchange exchange) {
//        return exchange.getSession()
//                .doOnNext(session -> session.getAttributes().put("init", true))
//                .map(WebSession::getId);
//    }
//}

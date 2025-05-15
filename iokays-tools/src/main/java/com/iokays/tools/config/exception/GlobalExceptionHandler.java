package com.iokays.tools.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<String>> handleAllExceptions(Exception ex, ServerWebExchange exchange) {
        // 获取请求的 URI
        final var requestUri = exchange.getRequest().getURI().toString();

        log.error("未处理异常 - URI: {}", requestUri, ex);

        // 获取堆栈跟踪信息
        final var  sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        final var  stackTrace = sw.toString();

        // 直接返回堆栈信息
        final var errorResponse = String.format("服务器内部错误: %s\n请求URI: %s\n堆栈跟踪:\n%s",
                ex.getMessage(), requestUri, stackTrace);

        // 返回 Mono 包装的 ResponseEntity，包含错误堆栈字符串
        return Mono.just(new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR));
    }
}

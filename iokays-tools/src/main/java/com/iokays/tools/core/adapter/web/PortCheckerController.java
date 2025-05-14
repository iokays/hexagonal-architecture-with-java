package com.iokays.tools.core.adapter.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
@RequestMapping("/portChecker")
public class PortCheckerController {

    private static final int DEFAULT_TIMEOUT = 1000;
    private static final int MAX_PORTS_PER_REQUEST = 1000;

    @GetMapping
    public Flux<String> scanPorts(@RequestParam String domain, @RequestParam List<Integer> ports) {

        log.info("开始扫描 {} 的 {} 个端口: {}", domain, ports.size(), ports);

        AtomicInteger openPorts = new AtomicInteger(0);

        // 扫描所有端口并返回状态
        Flux<String> portStatusFlux = Flux.fromIterable(ports)
                .parallel()
                .runOn(Schedulers.boundedElastic())
                .flatMap(port -> checkPort(domain, port)
                        .map(isOpen -> {
                            if (isOpen) {
                                openPorts.incrementAndGet();
                                return String.format("主机: %s 端口: %d 状态: 开放\n", domain, port);
                            } else {
                                return String.format("主机: %s 端口: %d 状态: 关闭\n", domain, port);
                            }
                        })
                        .onErrorResume(e -> Mono.just(
                                String.format("主机: %s 端口: %d 状态: 检查失败(%s)", domain, port, e.getMessage()))))
                .sequential();

        // 添加统计信息到结果流末尾
        return portStatusFlux.concatWithValues(
                        String.format("扫描完成 - 主机: %s 总端口数: %d 开放端口: %d 关闭端口: %d",
                                domain, ports.size(), openPorts.get(), ports.size() - openPorts.get()))
                .doOnComplete(() ->
                        log.info("扫描 {} 完成，共扫描 {} 个端口，其中 {} 个开放",
                                domain, ports.size(), openPorts.get()));
    }

    private Mono<Boolean> checkPort(String domain, int port) {
        return Mono.fromCallable(() -> {
                    try (Socket socket = new Socket()) {
                        socket.connect(new InetSocketAddress(domain, port), DEFAULT_TIMEOUT);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                })
                .timeout(Duration.ofMillis(DEFAULT_TIMEOUT + 500), Mono.just(false))
                .subscribeOn(Schedulers.boundedElastic());
    }
}

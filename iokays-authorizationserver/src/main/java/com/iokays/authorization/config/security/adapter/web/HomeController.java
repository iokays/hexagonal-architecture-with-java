package com.iokays.authorization.config.security.adapter.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.util.stream.IntStream;

@Slf4j
@RestController
@AllArgsConstructor
public class HomeController {

    @GetMapping(value = "/")
    public String hello() {
        log.info("home() has been called");

        IntStream.range(0, 10).parallel().forEach(v -> {
            //不支持 请查看: https://github.com/open-telemetry/opentelemetry-java-instrumentation/issues/709
            log.info("stream.parallel.forEach.home() has been called");
        });

        return "Hello World!";
    }

    /**
     * 302跳转
     */
    @GetMapping(value = "/302")
    public View redirect(@RequestParam(required = false) String url) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(url); // 可以是相对路径或绝对URL
        redirectView.setContextRelative(false); // 如果设置为true，则URL为相对路径（相对于应用上下文）
        return redirectView;
    }

}

package com.iokays.common.traceid;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @Resource
    private SampleLoggerService service;


    @GetMapping("/ping")
    public String ping() throws InterruptedException {
        service.show();
        return "pong";
    }

}

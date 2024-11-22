package com.iokays.sample;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ClassUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class SampleController {

    @GetMapping("/")
    public String hello() throws JsonProcessingException {
        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("principal.class: {}, object: {}", ClassUtils.getName(principal), principal);
        return "hello";
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

}

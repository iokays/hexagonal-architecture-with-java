package com.iokays.sample

import org.apache.commons.lang3.ClassUtils
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController {

    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/")
    fun hello(): String {
        val principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal()

        log.info("principal.class: {}, object: {}", ClassUtils.getName(principal), principal)

        return "hello"
    }

    @GetMapping("/ping")
    fun ping(): String {
        return "pong"
    }


}
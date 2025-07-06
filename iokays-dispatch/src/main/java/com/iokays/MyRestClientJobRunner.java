//package com.iokays;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.iokays.dispatch.core.adapter.job.JobClass;
//import com.iokays.dispatch.core.adapter.job.rest.RestClientData;
//import com.iokays.dispatch.core.application.service.JobApplicationService;
//import com.iokays.dispatch.core.application.service.command.CreateJob;
//import io.vavr.control.Try;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.Map;
//
//@Slf4j
//@Order
//@Component
//@AllArgsConstructor
//public class MyRestClientJobRunner implements CommandLineRunner {
//
//    private final JobApplicationService jobApplicationService;
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @Override
//    public void run(String... args) {
//        Try.run(() -> {
//            final var data = RestClientData.builder()
//                    .url("https://www.iokays.com/api/authorization/users")
//                    .method("get")
//                    .headers(Map.of("Content-Type", "application/form-urlencoded"))
//                    .body("".getBytes())
//                    .build();
//
//
//            jobApplicationService.scheduleJob(CreateJob.builder()
//                    .name("RestClientJob")
//                    .group("RestClientJobGroup")
//                    .startAt(LocalDateTime.now())
//                    .endAt(LocalDateTime.now().plusMinutes(60))
//                    .jobClass(JobClass.REST_CLIENT)
//                    .cronExpression("0/5 * * * * ?")
//                    .input(data)
//                    .build());
//        }).isSuccess();
//    }
//}

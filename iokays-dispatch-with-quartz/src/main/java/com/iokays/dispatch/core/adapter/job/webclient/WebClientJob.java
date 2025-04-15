package com.iokays.dispatch.core.adapter.job.webclient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@AllArgsConstructor
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class WebClientJob implements Job {

    private final WebClient webClient;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // 编写任务内容
    @Override
    public void execute(JobExecutionContext context) {
        // 获取JobDetail
        JobDetail jobDetail = context.getJobDetail();

        // 可以输出任务的一些详情信息
//        log.info("任务名称：{}", jobDetail.getKey().getName());
//        log.info("任务分组名称：{}", jobDetail.getKey().getGroup());
//        log.info("任务类名字：{}", jobDetail.getJobClass().getName());
//        log.info("本次任务执行时间：{}", context.getFireTime());
//        log.info("下次任务执行时间：{}", context.getNextFireTime());

        // 还可以通过JobDataMap存储数据
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        final var content = jobDataMap.get("webClient");
        if (content instanceof WebClientData webClientData) {
            log.info("webClientData: {}", webClientData);
            final var responseMono = send(webClientData);
            final var success = isSuccessResponse(responseMono).block();
            if (Boolean.TRUE.equals(success)) {
                return;
            }
            throw new RuntimeException("请求失败");
        } else {
            log.error("webClientData is not WebClientData: {}", content);
            throw new IllegalArgumentException("webClientData is null");
        }
    }

    private Mono<ClientResponse> send(WebClientData data) {
        WebClient.RequestBodySpec request = webClient
                .method(HttpMethod.valueOf(data.method()))
                .uri(data.url())
                .headers(httpHeaders -> {
                    if (data.headers() != null) {
                        httpHeaders.setAll(data.headers());
                    }
                });

        WebClient.RequestHeadersSpec<?> requestSpec;

        if (data.body() != null && data.body().length > 0) {
            requestSpec = request.bodyValue(data.body());
        } else {
            requestSpec = request;
        }

        return requestSpec.exchangeToMono(Mono::just);
    }

    private static Mono<Boolean> isSuccessResponse(Mono<ClientResponse> responseMono) {
        return responseMono.flatMap(response -> {
            log.info("response: {}", response);
            int status = response.statusCode().value();

            if (status == 200) {
                return response.bodyToMono(String.class)
                        .map(body -> {
                            try {
                                JsonNode json = objectMapper.readTree(body);
                                boolean success = json.has("code") && json.get("code").asInt() == 0;

                                if (success) {
                                    log.info("✅ 请求成功: code=0, body={}", body);
                                } else {
                                    log.warn("⚠️ 请求失败: code!=0, body={}", body);
                                }

                                return success;
                            } catch (Exception e) {
                                log.error("❌ 解析响应体失败，body 可能不是合法的 JSON", e);
                                return false;
                            }
                        });
            } else {
                log.warn("❌ 请求失败，HTTP 状态码: {}", status);
                return Mono.just(false);
            }
        });
    }
}

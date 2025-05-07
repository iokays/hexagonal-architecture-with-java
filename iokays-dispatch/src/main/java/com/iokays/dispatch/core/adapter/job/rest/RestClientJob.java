package com.iokays.dispatch.core.adapter.job.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iokays.dispatch.core.adapter.job.JobClass;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.quartz.*;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class RestClientJob implements Job {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final RestTemplate restTemplate;

    // 编写任务内容
    @Override
    public void execute(JobExecutionContext context) {
        // 获取JobDetail
        JobDetail jobDetail = context.getJobDetail();

        // 可以输出任务的一些详情信息
        log.info("任务名称：{}", jobDetail.getKey().getName());
        log.info("任务分组名称：{}", jobDetail.getKey().getGroup());
        log.info("任务类名字：{}", jobDetail.getJobClass().getName());
        log.info("本次任务执行时间：{}", context.getFireTime());
        log.info("下次任务执行时间：{}", context.getNextFireTime());

        // 还可以通过JobDataMap存储数据
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        final var content = jobDataMap.get(JobClass.REST_CLIENT.name());
        if (content instanceof RestClientData data) {
            log.info("webClientData: {}", data);
            final var response = send(data);
            if (isSuccess(response)) {
                return;
            }
            throw new RuntimeException("请求失败");
        } else {
            log.error("webClientData is not WebClientData: {}", content);
            throw new IllegalArgumentException("webClientData is null");
        }
    }

    private ResponseEntity<String> send(RestClientData data) {
        Validate.notNull(data, "webClientData is null");
        // 构建 URL（含 query params）
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(data.url());
        if (data.params() != null) {
            data.params().forEach(uriBuilder::queryParam);
        }

        HttpMethod httpMethod = Optional.of(data).map(RestClientData::method).map(String::toUpperCase).map(HttpMethod::valueOf).orElse(null);
        if (httpMethod == null) {
            throw new IllegalArgumentException("Invalid HTTP method: " + null);
        }

        HttpHeaders headers = new HttpHeaders();

        // 加入自定义 headers
        if (data.headers() != null) {
            data.headers().forEach(headers::add);
        }

        // 如果没设置 Content-Type，默认加上
        if (!headers.containsKey(HttpHeaders.CONTENT_TYPE)) {
            headers.setContentType(MediaType.APPLICATION_JSON);
        }

        HttpEntity<byte[]> entity = new HttpEntity<>(data.body(), headers);

        return restTemplate.exchange(
                uriBuilder.toUriString(),
                httpMethod,
                entity,
                String.class
        );
    }

    private boolean isSuccess(ResponseEntity<String> response) {
        log.info("response: {}", response);
        if (response.getStatusCode().is2xxSuccessful()) {
            return true;
        }
        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            return root.path("code").asInt() == 200;
        } catch (Exception e) {
            // 如果不是合法 JSON 或没有 code 字段，只根据 HTTP 状态判断
            log.error("Error parsing response body: {}", e.getMessage());
            return true;
        }
    }

}

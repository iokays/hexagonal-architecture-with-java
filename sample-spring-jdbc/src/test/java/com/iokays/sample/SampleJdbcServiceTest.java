package com.iokays.sample;

import com.google.common.collect.Lists;
import jakarta.annotation.Resource;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.concurrent.Executors;

/**
 * 本次采
 */
@Rollback(value = false)
@SpringBootTest
class SampleJdbcServiceTest {

    private static final Logger log = LoggerFactory.getLogger(SampleJdbcServiceTest.class);

    @Resource
    private SampleJdbcService service;

    /**
     * 生成指定大小的文本
     *
     * @param byteSize
     * @param listSize
     * @return
     */
    private static List<String> create(final int byteSize, final int listSize) {
        final var faker = new Faker();

        final List<String> result = Lists.newArrayList();
        while (result.size() <= listSize) {
            StringBuilder content = new StringBuilder();
            while (content.length() < byteSize) {
                // 使用Faker生成随机文本
                String sentence = faker.lorem().sentence();
                content.append(sentence).append(" ");
            }
            result.add(content.toString());
        }
        log.info("数据已准备");
        return result;

    }

    public static void main(String[] args) {
        System.out.println(create(4000, 1));

    }

    /**
     * mysql, 10w, 1tc, 26s
     */
    @Test
    void testInsert() {
        final var content = create(4000, 10 * 10000);
        final var now = System.currentTimeMillis();
        service.insert("t_content", content, 1000);
        log.info("耗时: {}", System.currentTimeMillis() - now);
    }

    @Test
    void testMultiInsert() {
        final var content = create(4000, 10 * 10000);
        final var now = System.currentTimeMillis();
        service.multiInsert("t_content", content, 1000);
        log.info("耗时: {}", System.currentTimeMillis() - now);
    }

    /**
     * 测试 Stream 并行处理
     * mysql, 10w, 1tc, 49s
     */
    @Test
    void testParallelInsert() {
        final var content = create(4000, 10 * 10000);
        final var now = System.currentTimeMillis();
        Lists.partition(content, 8).stream().parallel().forEach(v -> {
            service.insert("t_content", v, 1000);
        });
        log.info("耗时: {}", System.currentTimeMillis() - now);
    }

    /**
     * mysql, 10w, 1tc, 46s
     */
    @Test
    void testParallelMultiInsert() {
        final var content = create(4000, 10 * 10000);
        final var now = System.currentTimeMillis();
        Lists.partition(content, 8).stream().parallel().forEach(v -> {
            service.multiInsert("t_content", v, 1000);
        });
        log.info("耗时: {}", System.currentTimeMillis() - now);
    }

    /**
     * 多线程插入测试
     * mysql, 10w, 1tc, 1.24s
     */
    @Test
    void testMultiThreadsInsert() {
        final var content = create(4000, 10 * 10000);
        final var now = System.currentTimeMillis();

        final var nThreads = 8;
        try (final var executorService = Executors.newFixedThreadPool(nThreads)) {
            Lists.partition(content, nThreads).forEach(v -> executorService.execute(() -> service.insert("t_content", v, 1000)));
            service.multiInsert("t_content", content, 1000);
        }

        log.info("耗时: {}", System.currentTimeMillis() - now);
    }
}

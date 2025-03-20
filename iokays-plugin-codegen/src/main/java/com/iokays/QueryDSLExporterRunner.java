package com.iokays;

import com.querydsl.codegen.BeanSerializer;
import com.querydsl.codegen.JavaTypeMappings;
import com.querydsl.sql.codegen.MetaDataExporter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.File;
import java.io.Serializable;

@Slf4j
@Order
@Component
@AllArgsConstructor
public class QueryDSLExporterRunner implements CommandLineRunner, Serializable {

    private final DataSource dataSource;

    private final String modelName = "iokays-job-with-quartz";
    private final String packageName = "com.iokays.job.core.adapter.persistence.quartz.table";

    @Override
    public void run(String... args) throws Exception {
        log.info("开始生成实体");
        try (final var connection = dataSource.getConnection()) {
            final var exporter = new MetaDataExporter();
            exporter.setExportViews(false);
            exporter.setTableNamePattern("QRTZ_%");
            exporter.setPackageName(packageName);
            final var javaTypeMappings = new JavaTypeMappings();
            exporter.setTypeMappings(javaTypeMappings);
            exporter.setBeanSerializer(new BeanSerializer()); //生成Bean 类
            exporter.setTargetFolder(new File("%s/src/main/java".formatted(modelName)));
            exporter.export(connection.getMetaData());
        } catch (Exception e) {
            log.error("生成异常: ", e);
        }
    }
}

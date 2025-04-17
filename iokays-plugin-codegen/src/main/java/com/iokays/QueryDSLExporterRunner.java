package com.iokays;

import com.querydsl.codegen.BeanSerializer;
import com.querydsl.codegen.JavaTypeMappings;
import com.querydsl.sql.codegen.MetaDataExporter;
import jakarta.annotation.Resource;
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

    @Resource(name = "messageDataSource")
    private final DataSource messageDataSource;

    @Resource(name = "qrtzDataSource")
    private final DataSource qrtzDataSource;

    @Override
    public void run(String... args) throws Exception {
        this.genMessage();
    }

    private void genQrtz() {
        final String modelName = "iokays-dispatch";
        final String packageName = "com.iokays.dispatch.core.adapter.persistence.quartz.table";
        log.info("开始生成实体");
        try (final var connection = qrtzDataSource.getConnection()) {
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

    private void genMessage() {
        final String modelName = "iokays-dispatch";
        final String packageName = "com.iokays.dispatch.core.adapter.persistence.message.table";
        log.info("开始生成实体");
        try (final var connection = messageDataSource.getConnection()) {
            final var exporter = new MetaDataExporter();
            exporter.setExportViews(false);
            exporter.setTableNamePattern("T_LOCAL%");
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

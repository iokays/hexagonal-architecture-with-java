package com.iokays;

import com.querydsl.codegen.BeanSerializer;
import com.querydsl.codegen.JavaTypeMappings;
import com.querydsl.sql.codegen.MetaDataExporter;
import io.vavr.control.Try;
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
    private final String modelName = "iokays-dispatch";
    private final String packageName = "com.iokays.dispatch.core.adapter.persistence.message.table";
    private final String prefixToStrip = "int_";

    @Override
    public void run(String... args) throws Exception {
        this.gen();
    }

    private void gen() {
        log.info("开始生成实体");

        Try.run(() -> {
            final var exporter = new MetaDataExporter();
            exporter.setExportViews(false);
            exporter.setTableNamePattern(prefixToStrip + "%");  // 仅匹配以 int_ 开头的表
            exporter.setPackageName(packageName);
            exporter.setTypeMappings(new JavaTypeMappings());
            exporter.setBeanSerializer(new BeanSerializer());

            exporter.setNamingStrategy(new DynamicPrefixNamingStrategy(prefixToStrip)); // 直接传前缀

            exporter.setTargetFolder(new File("%s/src/main/java".formatted(modelName)));
            exporter.export(dataSource.getConnection().getMetaData());
        }).onFailure(e -> log.error("生成实体失败", e));

    }


}

package com.iokays.common.core.adapter.persiatence.jpa;

import com.iokays.common.core.domain.enums.FieldOperator;
import com.iokays.common.core.domain.enums.FieldType;
import com.iokays.common.core.domain.enums.SpecificationOperator;
import com.iokays.common.core.domain.enums.SpecificationType;
import com.iokays.common.core.domain.specification.FieldMapping;
import com.iokays.common.core.domain.specification.FieldSpecification;
import com.iokays.common.core.domain.specification.TextFieldContent;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class FieldSpecificationJpaRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(FieldSpecificationJpaRepositoryTest.class);

    @Resource
    private FieldSpecificationJpaRepository fieldSpecificationJpaRepository;

    @Test
    void test() {
        fieldSpecificationJpaRepository.findAll();

        final FieldMapping fieldMapping = new FieldMapping("name", FieldType.TEXT, "姓名");

        final TextFieldContent textFieldContent = new TextFieldContent("张三", null);

        final FieldSpecification entity = new FieldSpecification(
                fieldMapping,
                FieldOperator.EQUALS,
                textFieldContent,
                1,
                SpecificationType.FIELD,
                SpecificationOperator.AND
        );

        fieldSpecificationJpaRepository.save(entity);

        logger.info("test: {}", entity.test(Map.of("name", "张三")));

    }






}
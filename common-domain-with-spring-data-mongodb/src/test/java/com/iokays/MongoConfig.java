//package com.iokays;
//
//import com.iokays.common.domain.mongodb.AbstractIdConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
//
//@Configuration
//public class MongoConfig {
//
//    @Bean
//    public MongoCustomConversions mongoCustomConversions() {
//        return MongoCustomConversions.create(v -> v.registerConverter(new AbstractIdConverter()));
//    }
//}
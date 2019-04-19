package com.ri.eurekaclientorder.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.ri.eurekaclientorder.converter.BigDecimalToDecimal128Converter;
import com.ri.eurekaclientorder.converter.Decimal128ToBigDecimalConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MongodbConfig extends AbstractMongoConfiguration {

    @Value("${mongodb.dbName}")
    private String dbName;

    @Value("${spring.data.mongodb.uri}")
    private String mongodbURI;

    @Override
    public MongoClient mongoClient() {
        return new MongoClient();
    }

    @Override
    protected String getDatabaseName() {
        return dbName;
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter() {
        DefaultDbRefResolver dbRefResolver = new DefaultDbRefResolver(this.dbFactory());
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, this.mongoMappingContext());
        List<Object> list = new ArrayList<>();
        list.add(new BigDecimalToDecimal128Converter());//自定义的类型转换器
        list.add(new Decimal128ToBigDecimalConverter());//自定义的类型转换器
        converter.setCustomConversions(new MongoCustomConversions(list));
        return converter;
    }

    @Bean
    public MongoDbFactory dbFactory() {
        return new SimpleMongoDbFactory(new MongoClientURI(mongodbURI));
    }

    @Bean
    public MongoMappingContext mongoMappingContext() {
        return new MongoMappingContext();
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(this.dbFactory(), this.mappingMongoConverter());
    }
}
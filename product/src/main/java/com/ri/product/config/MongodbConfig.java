//package com.ri.product.config;
//
//import com.ri.product.converter.BigDecimalToDecimal128Converter;
//import com.ri.product.converter.Decimal128ToBigDecimalConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.core.convert.CustomConversions;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//public class MongodbConfig {
//
//    @Bean
//    public CustomConversions customConversions() {
//        List list = new ArrayList();
//        list.add(new BigDecimalToDecimal128Converter());
//        list.add(new Decimal128ToBigDecimalConverter());
//        return new CustomConversions(list);
//    }
//
//}

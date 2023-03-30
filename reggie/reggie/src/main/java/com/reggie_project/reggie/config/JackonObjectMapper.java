package com.reggie_project.reggie.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class JackonObjectMapper extends ObjectMapper {   //消息转换器
      private static final String DEFAULT_DATE_FORMAT ="yyyy-MM-dd";
      private static  final String DEFAULT_DATE_TIME_FORMAT ="yyyy-MM-dd HH:mm:ss";
      private  static final String DEFAULT_TIME_FORMAT ="HH:MM:ss";
    //  java转json  json转Java
      public JackonObjectMapper() {
          // 收到未知属性时不报错
           this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
           //反序列化 属性不存在
           this.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);

          SimpleModule simpleModule;
          simpleModule =new SimpleModule();
          simpleModule.addDeserializer(LocalDateTime.class,new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)));
          simpleModule.addDeserializer(LocalTime.class,new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)));
          simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);
          simpleModule.addSerializer(Long.class,ToStringSerializer.instance);
          simpleModule.addSerializer(LocalDateTime.class,new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)));
          simpleModule.addSerializer(LocalDate.class,new LocalDateSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)));
          simpleModule.addSerializer(LocalTime.class,new LocalTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)));

          this.registerModule(simpleModule);
      }
}

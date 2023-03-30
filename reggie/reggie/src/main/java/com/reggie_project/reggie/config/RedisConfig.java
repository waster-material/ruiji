package com.reggie_project.reggie.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class RedisConfig {
      @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
          RedisTemplate<String,Object> redisTemplate =new RedisTemplate<>();
          redisTemplate.setConnectionFactory(redisConnectionFactory);

          Jackson2JsonRedisSerializer<Object>  serializer =new Jackson2JsonRedisSerializer<Object>(Object.class);
          ObjectMapper mapper =new ObjectMapper();
          mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
          mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
          serializer.setObjectMapper(mapper);

          redisTemplate.setValueSerializer(serializer);
          redisTemplate.setHashKeySerializer(serializer);
          redisTemplate.afterPropertiesSet();
          return redisTemplate;
      }
}

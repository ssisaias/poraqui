package com.qxd.poraqui.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.qxd.poraqui.model.Local;


@Configuration
public class DatabaseConfiguration {

	@Inject
	private JedisConnectionFactory jedisConnFactory;
	
    @Bean
    public StringRedisSerializer stringRedisSerializer() {
    	StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
    	return stringRedisSerializer;
    }
    
    @Bean
    public JacksonJsonRedisSerializer<Local> jacksonJsonRedisJsonSerializer() {
    	JacksonJsonRedisSerializer<Local> jacksonJsonRedisJsonSerializer = new JacksonJsonRedisSerializer<>(Local.class);
    	return jacksonJsonRedisJsonSerializer;
    }
    
	@Bean
	public RedisTemplate<String, Local> redisTemplate() {
		RedisTemplate<String, Local> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnFactory);
		redisTemplate.setKeySerializer(stringRedisSerializer());
		redisTemplate.setValueSerializer(jacksonJsonRedisJsonSerializer());
		return redisTemplate;
	}
}

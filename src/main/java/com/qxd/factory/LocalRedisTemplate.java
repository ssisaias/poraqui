package com.qxd.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.qxd.model.Local;


public class LocalRedisTemplate {
	
	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;
	
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
		redisTemplate.setConnectionFactory(jedisConnectionFactory);
		redisTemplate.setKeySerializer(stringRedisSerializer());
		redisTemplate.setValueSerializer(jacksonJsonRedisJsonSerializer());
		return redisTemplate;
	}
}

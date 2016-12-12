package com.qxd.poraqui.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.qxd.poraqui.model.Local;



@Configuration
public class MyJedisConnectionFactoryConfig {
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		  return new JedisConnectionFactory();
	}
	

	
}

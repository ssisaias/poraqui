package com.qxd.poraqui.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.qxd.poraqui.model.Local;

@Repository
public class LocalRepository  {
	
	@Inject
	private RedisTemplate<String, Local> redisTemplate;
	
	@Inject
	private RedisTemplate<String, String> strTemplate;
	
	public void save(Local local) {
		redisTemplate.opsForValue().set(local.getId(), local);
		strTemplate.opsForValue().set("avalCount:"+local.getId(), "0"); //Contador de avaliacoes desse local (postagem)
	}
 
	public Local findById(String key) {
		return redisTemplate.opsForValue().get(key);
	}
	
	public List<Local> findAll() {
		List<Local> locals = new ArrayList<>();
		
		Set<String> keys = redisTemplate.keys("loc*");
		Iterator<String> it = keys.iterator();
		
		while(it.hasNext()){
			locals.add(findById(it.next()));
		}
		
		return locals;
	}
	
	public void delete(Local l) {
		String key = l.getId();
		redisTemplate.opsForValue().getOperations().delete(key);
	}
	
	 
	public void deleteAll() {
		Set<String> keys = redisTemplate.keys("*");
		Iterator<String> it = keys.iterator();
		
		while(it.hasNext()){
			Local b = new Local(it.next());
		    delete(b);
		}
	}	
	
	
}

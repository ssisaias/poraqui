package com.qxd.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.qxd.model.Local;

/*
 * Repositorio Redis do Local 
 */
@Repository
public class LocalRepository {
	
	@Autowired
	private RedisTemplate<String, Local> redisTemplate;
	
	public void save(Local local) {
		redisTemplate.opsForValue().set(local.getId(),local);
	}
 
	public Local findById(String key) {
		return redisTemplate.opsForValue().get(key);
	}
	
	public List<Local> findAll() {
		List<Local> books = new ArrayList<>();
		
		Set<String> keys = redisTemplate.keys("*");
		Iterator<String> it = keys.iterator();
		
		while(it.hasNext()){
			books.add(findById(it.next()));
		}
		
		return books;
	}
	
	public void delete(Local l) {
		String key = l.getId();
		redisTemplate.opsForValue().getOperations().delete(key);
	}
	
	 
	public void deleteAll() {
		Set<String> keys = redisTemplate.keys("*");
		Iterator<String> it = keys.iterator();
		
		while(it.hasNext()){
			Local l = new Local(it.next());
		    delete(l);
		}
	}
}

package com.qxd.poraqui.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.qxd.poraqui.model.Avaliacao;
import com.qxd.poraqui.model.Local;

@Repository
public class AvaliacaoRepository {
	
	@Inject
	private RedisTemplate<String, String> redisTemplate;
	@Inject
	private RedisTemplate<String, Avaliacao> redisTemplateAval;
	
	
	public void save(Avaliacao avaliacao) {
		Local local = avaliacao.getLocal();
		redisTemplate.opsForValue().increment("avalCount:"+local.getId(),1);
		String avalCount = redisTemplate.opsForValue().get("avalCount:"+local.getId());
		//Regra de chave da avaliacao: avaliacao:id:idLocal
		//o incremento do id de avaliacao é POR LOCAL por isso pode haver duas avaliacoes 2 por exemplo, mas sao de locais diferentes
		avaliacao.setId("avaliacao:"+avalCount+":"+local.getId());
		redisTemplateAval.opsForValue().set(avaliacao.getId(), avaliacao);
	}
 
	public Avaliacao findById(String key) {
		return redisTemplateAval.opsForValue().get(key);
	}
	
	public List<Avaliacao> findAll() {
		List<Avaliacao> locals = new ArrayList<>();
		
		Set<String> keys = redisTemplate.keys("*");
		Iterator<String> it = keys.iterator();
		
		while(it.hasNext()){
			locals.add(findById(it.next()));
		}
		
		return locals;
	}
	
	public void delete(Avaliacao l) {
		String key = l.getId();
		redisTemplate.opsForValue().getOperations().delete(key);
	}
	
	 
	public void deleteAll() {
		Set<String> keys = redisTemplate.keys("*");
		Iterator<String> it = keys.iterator();
		
		while(it.hasNext()){
			Avaliacao b = new Avaliacao(it.next());
		    delete(b);
		}
	}	
}

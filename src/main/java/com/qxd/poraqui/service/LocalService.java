package com.qxd.poraqui.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qxd.poraqui.model.Avaliacao;
import com.qxd.poraqui.model.Local;

@Service
public interface LocalService {

	Local get(Long id);
	
	List<Local> getAll();
	
	Local save(Local local);
	
	boolean remove(Local local);
	
	Avaliacao addAvaliacao(Avaliacao avaliacao);
	
}

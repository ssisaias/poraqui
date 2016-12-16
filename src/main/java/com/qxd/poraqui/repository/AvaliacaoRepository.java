package com.qxd.poraqui.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qxd.poraqui.model.Avaliacao;
import com.qxd.poraqui.model.Local;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {
	
	Local findById(Long id);

}

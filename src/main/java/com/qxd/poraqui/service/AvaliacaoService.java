package com.qxd.poraqui.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.qxd.poraqui.model.Avaliacao;
import com.qxd.poraqui.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {

	final Logger logger = LoggerFactory.getLogger(AvaliacaoService.class);

	@Inject
	private AvaliacaoRepository avaliacaoRepository;

	public List<Avaliacao> findAll() {
		return Lists.newArrayList(avaliacaoRepository.findAll());
	}

	public Avaliacao findById(String id) {
		return avaliacaoRepository.findById(id);
	}

	public void save(Avaliacao avaliacao) {
		avaliacaoRepository.save(avaliacao);
	}

	public void delete(Avaliacao avaliacao) {
		avaliacaoRepository.delete(avaliacao);
	}

	/**
	 * Removes all Local entities from database.
	 */
	public void deleteAll() {
		avaliacaoRepository.deleteAll();
	}


	
}

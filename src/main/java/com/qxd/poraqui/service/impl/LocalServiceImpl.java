package com.qxd.poraqui.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.qxd.poraqui.model.Avaliacao;
import com.qxd.poraqui.model.Local;
import com.qxd.poraqui.repository.AvaliacaoRepository;
import com.qxd.poraqui.repository.LocalRepository;
import com.qxd.poraqui.service.LocalService;

@Named
public class LocalServiceImpl implements LocalService {

	@Inject
	private LocalRepository localRepository;
	
	@Inject
	private AvaliacaoRepository avaliacaoRepository;

	@Override
	public Local get(Long id) {
		return localRepository.findById(id);
	}

	@Override
	public List<Local> getAll() {
		return localRepository.findAll();
	}

	@Override
	public Local save(Local local) {
		return localRepository.save(local);
	}

	@Override
	public boolean remove(Local local) {
		localRepository.delete(local);
		return true;
	}
	
	@Override
	public Avaliacao addAvaliacao(Avaliacao avaliacao) {
		return avaliacaoRepository.save(avaliacao);
	}
	
}

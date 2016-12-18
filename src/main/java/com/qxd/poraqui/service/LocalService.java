package com.qxd.poraqui.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.qxd.poraqui.model.Avaliacao;
import com.qxd.poraqui.model.Local;
import com.qxd.poraqui.repository.LocalRepository;
import com.google.common.collect.Lists;

@Service
public class LocalService {

	final Logger logger = LoggerFactory.getLogger(LocalService.class);

	@Inject
	private LocalRepository localRepository;

	public List<Local> findAll() {
		return Lists.newArrayList(localRepository.findAll());
	}

	public Local findById(String id) {
		return localRepository.findById(id);
	}

	public void save(Local local) {
		localRepository.save(local);
	}

	public void delete(Local local) {
		localRepository.delete(local);
	}

	/**
	 * Removes all Local entities from database.
	 */
	public void deleteAll() {
		localRepository.deleteAll();
	}


	
}

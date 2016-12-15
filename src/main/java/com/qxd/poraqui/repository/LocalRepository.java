package com.qxd.poraqui.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qxd.poraqui.model.Local;

public interface LocalRepository extends JpaRepository<Local, Integer> {
	
	Local findById(Long id);

}

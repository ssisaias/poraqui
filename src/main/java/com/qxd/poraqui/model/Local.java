package com.qxd.poraqui.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.data.repository.NoRepositoryBean;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.qxd.poraqui.enumtype.TipoAcessibilidade;

public class Local {
	
	private String id;
	
	private String nome;
	private String descricao;
	
	private double latitude;
	private double longitude;
	
	@Enumerated(EnumType.STRING)
	private TipoAcessibilidade acessibilidade;
	
	//@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE }, mappedBy = "local")
	//@JsonManagedReference
	//private List<Avaliacao> avaliacoes;

	public Local() {}

	public Local(String id, String nome, String descricao, double latitude, double longitude,
			TipoAcessibilidade acessibilidade, List<Avaliacao> avaliacoes) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.latitude = latitude;
		this.longitude = longitude;
		this.acessibilidade = acessibilidade;
		//this.avaliacoes = avaliacoes;
	}

	public Local(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public TipoAcessibilidade getAcessibilidade() {
		return acessibilidade;
	}

	public void setAcessibilidade(TipoAcessibilidade acessibilidade) {
		this.acessibilidade = acessibilidade;
	}

//	public List<Avaliacao> getAvaliacoes() {
//		return avaliacoes;
//	}
//	
//	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
//		this.avaliacoes = avaliacoes;
//	}
	
}

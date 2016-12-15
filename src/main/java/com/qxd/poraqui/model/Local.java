package com.qxd.poraqui.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.qxd.poraqui.enumtype.TipoAcessibilidade;

@Entity
public class Local {
	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	private String descricao;
	
	private double latitude;
	private double longitude;
	
	private TipoAcessibilidade acessibilidade;

	public Local() {}
	
	public Local(Long id, String nome, String descricao, double latitude, double longitude,
			TipoAcessibilidade acessibilidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.latitude = latitude;
		this.longitude = longitude;
		this.acessibilidade = acessibilidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	
	
}

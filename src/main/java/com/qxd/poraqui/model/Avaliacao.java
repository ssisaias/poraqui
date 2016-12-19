package com.qxd.poraqui.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class Avaliacao {
	
	//@Id
	//@GeneratedValue
	private String id;
	
	//@Column
	@Size(max=1024)
	private String comentario;

	//@Column
	private int value;
	
	//@Column
	@Temporal(TemporalType.TIMESTAMP) 
	@DateTimeFormat (pattern="dd-MM-YYYY")
	private Date data;
	
	//@ManyToOne
	//@JsonBackReference
	private Local local;
	
	public Avaliacao() {}
	
	public Avaliacao(String id, String comentario, int value, Date data, Local local) {
		this.id = id;
		this.comentario = comentario;
		this.value = value;
		this.data = data;
		this.local = local;
	}

	public Avaliacao(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	@Override
	public String toString() {
		return "Avaliacao [id=" + id + ", comentario=" + comentario + ", value=" + value + ", data=" + data + ", local="
				+ local + "]";
	}

}

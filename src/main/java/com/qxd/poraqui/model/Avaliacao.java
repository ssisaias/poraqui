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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Avaliacao {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	@Size(max=1024)
	private String comentario;

	@Column
	private int value;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP) 
	@DateTimeFormat (pattern="dd-MM-YYYY")
	private Date data;
	
	@ManyToOne
	@JsonBackReference
	private Local local;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

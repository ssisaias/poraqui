package com.qxd.poraqui.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.format.annotation.DateTimeFormat;

//@Entity
@NoRepositoryBean
public class Publicacao {
	
	//@Id
	//@GeneratedValue
	private Long id;
	
	@Size(max=1024)
	private String comentario;
	
	@Temporal(TemporalType.TIMESTAMP) 
	@DateTimeFormat (pattern="dd-MM-YYYY")
	private Date data;
	
	//@ManyToOne
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
	
}

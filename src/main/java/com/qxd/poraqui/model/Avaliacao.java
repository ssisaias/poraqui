package com.qxd.poraqui.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Avaliacao {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Size(max=1024)
	private String comentario;

	private int value;
	
	@Temporal(TemporalType.TIMESTAMP) 
	@DateTimeFormat (pattern="dd-MM-YYYY")
	private Date data;
	
	@ManyToOne
	private Local local;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

}

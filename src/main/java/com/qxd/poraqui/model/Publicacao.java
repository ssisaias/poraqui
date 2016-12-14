package com.qxd.poraqui.model;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Publicacao {
	
	@Id
	@GeneratedValue
	private String itemFoto;
	private String comentario;
	@Temporal(TemporalType.TIMESTAMP) 
	@DateTimeFormat (pattern="dd-MM-YYYY")
	private Date data;
	private ArrayList<ItemPublicacaoFoto> fotos;
	public String getItemFoto() {
		return itemFoto;
	}
	public void setItemFoto(String itemFoto) {
		this.itemFoto = itemFoto;
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
	
	
	
	
	
}

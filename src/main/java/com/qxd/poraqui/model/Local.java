package com.qxd.poraqui.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.qxd.poraqui.enumtype.TipoAcessibilidade;

@Entity
public class Local {
	@Id
	@GeneratedValue
	public double latitude;
	public double longitude;
	public TipoAcessibilidade acessibilidade;
	
	public String id;
	
	public Local(String id) {
		this.id = id;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	

}

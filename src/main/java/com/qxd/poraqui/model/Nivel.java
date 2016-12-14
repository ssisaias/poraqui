package com.qxd.poraqui.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Nivel {
	
	@Id
	@GeneratedValue
	private float[] notas = { 1, 2, 3, 4, 5 };
    
	public Float calcularNota(){
		
		return 0.f;
	}

	public float[] getNotas() {
		return notas;
	}

	public void setNotas(float[] notas) {
		this.notas = notas;
	}

}

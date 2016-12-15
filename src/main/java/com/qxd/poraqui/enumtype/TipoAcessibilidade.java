package com.qxd.poraqui.enumtype;

public enum TipoAcessibilidade {

	VISUAL("Visual"), FISICA("Fisica"), AUDITIVA("Auditiva"), MENTAL("Mental");
	
	private String tipoAcessibilidade;

	private TipoAcessibilidade(String tipoAcessibilidade) {
		this.tipoAcessibilidade = tipoAcessibilidade;
	}

	public String getTipoAcessibilidade() {
		return tipoAcessibilidade;
	}

	public void setTipoAcessibilidade(String tipoAcessibilidade) {
		this.tipoAcessibilidade = tipoAcessibilidade;
	}

}

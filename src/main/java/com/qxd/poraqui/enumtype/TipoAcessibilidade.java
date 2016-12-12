package com.qxd.poraqui.enumtype;

public enum TipoAcessibilidade {

	VISUAL("Visual"), FISICA("Fisica"), AUDITIVA("Auditiva"), MENTAL("Mental");

	TipoAcessibilidade(String tipoAcessibilidade) {
		this.tipoAcessibilidade = tipoAcessibilidade;
	}

	public String getCurso() {
		return tipoAcessibilidade;
	}

	public void setCurso(String tipoAcessibilidade) {
		this.tipoAcessibilidade = tipoAcessibilidade;
	}

	private String tipoAcessibilidade;
}

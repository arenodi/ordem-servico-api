package com.andre.os.domain.enums;

public enum Prioridade {

	BAIXA(0, "BAIXA"),
	MEDIA(1, "MEDIA"),
	ALTA(2,"ALTA");
	
	private Integer codigoPrioridade;
	private String descricaoPrioridade;
	
	private Prioridade(Integer codigoPrioridade, String descricaoPrioridade) {
		this.codigoPrioridade = codigoPrioridade;
		this.descricaoPrioridade = descricaoPrioridade;
	}

	public Integer getCodigoPrioridade() {
		return codigoPrioridade;
	}

	public String getDescricaoPrioridade() {
		return descricaoPrioridade;
	}
	
	public static Prioridade toEnum(Integer codigoPrioridade) {
		
		if(codigoPrioridade == null) {
			return null;
		}
		
		for(Prioridade x : Prioridade.values()) {
			if(codigoPrioridade.equals(x.getCodigoPrioridade())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Prioridade inválida!" + codigoPrioridade);
	}
}

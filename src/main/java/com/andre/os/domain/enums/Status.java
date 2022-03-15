package com.andre.os.domain.enums;

public enum Status {

	ABERTO(0, "ABERTO"),
	ANDAMENTO(1, "ANDAMENTO"),
	ENCERRADO(2,"ENCERRADO");
	
	private Integer codigoStatus;
	private String descricaoStatus;
	
	private Status(Integer codigoStatus, String descricaoStatus) {
		this.codigoStatus = codigoStatus;
		this.descricaoStatus = descricaoStatus;
	}

	public Integer getCodigoStatus() {
		return codigoStatus;
	}

	public String getDescricaoStatus() {
		return descricaoStatus;
	}
	
	public static Status toEnum(Integer codigoStatus) {
		
		if(codigoStatus == null) {
			return null;
		}
		
		for(Status x : Status.values()) {
			if(codigoStatus.equals(x.getCodigoStatus())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Status inválido!" + codigoStatus);
	}
}

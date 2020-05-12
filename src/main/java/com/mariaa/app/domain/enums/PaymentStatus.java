package com.mariaa.app.domain.enums;

public enum PaymentStatus {
	
	PENDING(1, "Pendente"),
	SETTLED(2, "Quitado"),
	CANCELED(3, "Cancelado");
	
	private Integer code;
	private String description;
	
	private PaymentStatus() { }
	
	
	private PaymentStatus(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	
	public static PaymentStatus toEnum(Integer code) {
		if (code == null) {
			return null;
		}
		
		for (PaymentStatus p : PaymentStatus.values()) {
			if (code.equals(p.getCode())) {
				return p;
			}
		}
		
		throw new IllegalArgumentException("Código " + code + " inválido!");
	}
	
	
	
	
	
}

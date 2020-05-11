package com.mariaa.app.domain.enums;

public enum CustomerType {
	PERSON(1, "Pessoa Física"), 
	LEGAL_PERSON(2, "Pessoa Jurídica");
	
	private Integer code;
	private String description;
	
	private CustomerType(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static CustomerType toEnum(Integer code) {
		if (code == null) {
			return null;
		}
		
		for (CustomerType c : CustomerType.values()) {
			if (code.equals(c.getCode())) {
				return c;
			}
		}
		
		throw new IllegalArgumentException("Código inválido: " + code);
	}

}

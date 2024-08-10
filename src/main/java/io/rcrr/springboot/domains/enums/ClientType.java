package io.rcrr.springboot.domains.enums;

public enum ClientType {
	INDIVIDUAL(1, "Individual"),
	LEGAL_ENTITY(2, "Legal Entity");
	
	private int cod;
	private String description;
	
	private ClientType(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}
	
	public static ClientType toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(ClientType c : ClientType.values()) {
			if(cod.equals(c.getCod())) {
				return c;
			}
		}
		
		throw new IllegalArgumentException("Invalid ID: " + cod);
	}
}

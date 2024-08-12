package io.rcrr.springboot.domains.enums;

public enum StatePayment {
	PENDING(1, "Pending"), 
	PAID(2, "Paid"), 
	CANCELLED(3, "Cancelled");
	
	private int cod;
	private String description;
	
	private StatePayment(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public String getDescripton() {
		return description;
	}
	
	public static StatePayment toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(StatePayment c : StatePayment.values()) {
			if(cod.equals(c.getCod())) {
				return c;
			}
		}
		
		throw new IllegalArgumentException("Invalid ID: " + cod);
	}
}

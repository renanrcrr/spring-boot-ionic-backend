package io.rcrr.springboot.domains;

import io.rcrr.springboot.domains.enums.StatePayment;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="Pagamento_Com_Cartao")
public class PaymentWithCard extends Payment {
	private static final long serialVersionUID = 1L;
	
	private Integer numberOfInstalments;
	
	public PaymentWithCard() {}

	public PaymentWithCard(Integer id, StatePayment state, Order order, Integer numberOfInstalments) {
		super(id, state, order);
		this.numberOfInstalments = numberOfInstalments;
	}

	public Integer getNumberOfInstalments() {
		return numberOfInstalments;
	}

	public void setNumberOfInstalments(Integer numberOfInstalments) {
		this.numberOfInstalments = numberOfInstalments;
	}
}

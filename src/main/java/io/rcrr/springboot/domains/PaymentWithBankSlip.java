package io.rcrr.springboot.domains;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.rcrr.springboot.domains.enums.StatePayment;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="Pagamento_Com_Boleto")
public class PaymentWithBankSlip extends Payment {
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dueDate;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date paymentDate;
	
	public PaymentWithBankSlip() {}

	public PaymentWithBankSlip(Integer id, StatePayment state, Order order, Date dueDate, Date paymentDate) {
		super(id, state, order);
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
}

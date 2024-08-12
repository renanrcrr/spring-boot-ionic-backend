package io.rcrr.springboot.domains;

import java.util.Date;

import io.rcrr.springboot.domains.enums.StatePayment;

public class PaymentWithBankSlip extends Payment {
	private static final long serialVersionUID = 1L;
	
	private Date dueDate;
	private Date duePayment;
	
	public PaymentWithBankSlip() {}

	public PaymentWithBankSlip(Integer id, StatePayment statePayment, Order order, Date dueDate, Date duePayment) {
		super(id, statePayment, order);
		this.dueDate = dueDate;
		this.duePayment = duePayment;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getDuePayment() {
		return duePayment;
	}

	public void setDuePayment(Date duePayment) {
		this.duePayment = duePayment;
	}
}

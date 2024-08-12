package io.rcrr.springboot.domains;

import java.io.Serializable;
import java.util.Objects;

import io.rcrr.springboot.domains.enums.StatePayment;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Pagamento")
public class Payment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private StatePayment statePayment;
	
	@OneToOne
	@JoinColumn(name="order_id")
	@MapsId
	private Order order;
	
	public Payment() {}

	public Payment(Integer id, StatePayment statePayment, Order order) {
		super();
		this.id = id;
		this.statePayment = statePayment;
		this.order = order;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StatePayment getStatePayment() {
		return statePayment;
	}

	public void setStatePayment(StatePayment statePayment) {
		this.statePayment = statePayment;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		return Objects.equals(id, other.id);
	}
}

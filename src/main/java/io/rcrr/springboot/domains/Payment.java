package io.rcrr.springboot.domains;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.rcrr.springboot.domains.enums.StatePayment;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Pagamento")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Payment implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private Integer state;
	
	@JsonBackReference
	@OneToOne
	@JoinColumn(name="pedido_id")
	@MapsId
	private Order order;
	
	public Payment() {}

	public Payment(Integer id, StatePayment state, Order order) {
		super();
		this.id = id;
		this.state = state.getCod();
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StatePayment getState() {
		return StatePayment.toEnum(state);
	}

	public void setState(StatePayment state) {
		this.state = state.getCod();
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}

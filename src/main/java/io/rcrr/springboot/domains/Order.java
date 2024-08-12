package io.rcrr.springboot.domains;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Pedido")
public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Date moment;
	
	// Important to avoid the Entity Transient Error when saving an order and its payment
	@OneToOne(cascade=CascadeType.ALL, mappedBy="order")
	private Payment payment;
	
	private Client client;
	
	private Address deliveryAddress;
	
	private List<Product> items = new ArrayList<>();
	
	public Order() {}

	public Order(Integer id, Date moment, Payment payment, Client client, Address deliveryAddress) {
		super();
		this.id = id;
		this.moment = moment;
		this.payment = payment;
		this.client = client;
		this.deliveryAddress = deliveryAddress;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public List<Product> getItems() {
		return items;
	}

	public void setItems(List<Product> items) {
		this.items = items;
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
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}
}

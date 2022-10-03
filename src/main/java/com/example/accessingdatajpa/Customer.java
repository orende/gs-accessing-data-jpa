package com.example.accessingdatajpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "customer_id_seq")
	private Long id;
	private String firstName;
	private String lastName;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_fk")
	private Collection<Orders> orders;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tag_id")
	private Tag tags;

	protected Customer() {}

	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.orders = new ArrayList<>();
	}

	public Customer(String firstName, String lastName, List<Orders> orders, Tag tags) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.orders = orders;
		this.tags = tags;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Customer.class.getSimpleName() + "{", "}")
				.add("id=" + id)
				.add("firstName='" + firstName + "'")
				.add("lastName='" + lastName + "'")
				.add("orders=" + orders)
				.add("tags=" + tags)
				.toString();
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}
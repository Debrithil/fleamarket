package swd20.fleamarket.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String place;
	private double price;
	private String date;
	
	@ManyToOne // Reservation @ManyToOne Customer
	@JsonIgnore // estää @OneToMany:n, ettei @Entity vedä ikuista looppia
	@JoinColumn(name = "customerid") // foreign key
	private Customer customer; // mappedBy = omistajaluokka, johon se kytkeytyy -> customer
	
	public Reservation() {
	super();
	this.id = null;
	this.place = null;
	this.price = 0.00;
	this.date = null;
	
	}
	
	public Reservation(Long id, String place, double price, String date) {
		super();
		this.id = id;
		this.place = place;
		this.price = price;
		this.date = date;
		
	}
	
	public Reservation(String place, double price, String date, Customer customer) {
	super();
	this.place = place;
	this.price = price;
	this.date = date;
	this.customer = customer;
	
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", place=" + place + ", price=" + price + ", date=" + date + ", customer="
				+ customer + "]";
	}
		
}
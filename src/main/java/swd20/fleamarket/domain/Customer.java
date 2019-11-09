package swd20.fleamarket.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long customerid;
	private String name;
	private String phonenumber;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	private List<Reservation> reservations;
	
	public Customer() {}

	public Customer(String name, String phonenumber) {
		super();
		this.name = name;
		this.phonenumber = phonenumber;
	}

	public Long getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Long customerid) {
		this.customerid = customerid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	@Override
	public String toString() {
		return "Customer [customerid=" + customerid + ", name=" + name + ", phonenumber=" + phonenumber
				+ "]";
	}
	
}
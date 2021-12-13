package entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Address implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -832108451724515650L;
	private String street;
	private String city;
	private String state;
	private String potalCost;
	
	public Address() {
	}

	public Address(String street, String city, String state, String potalCost) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.potalCost = potalCost;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPotalCost() {
		return potalCost;
	}

	public void setPotalCost(String potalCost) {
		this.potalCost = potalCost;
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", state=" + state + ", potalCost=" + potalCost + "]";
	}
	
	
}

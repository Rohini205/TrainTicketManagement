package com.cloudBee.trainTicketMgt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SeatAllocation {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String section;
    private int seatNumber;
    private double price;
    
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "SeatAllocation [id=" + id + ", section=" + section + ", seatNumber=" + seatNumber + ", price=" + price
				+ ", user=" + user + "]";
	}

	public SeatAllocation(Long id, String section, int seatNumber, double price, User user) {
		super();
		this.id = id;
		this.section = section;
		this.seatNumber = seatNumber;
		this.price = price;
		this.user = user;
	}

	public SeatAllocation() {
		
	}
    
    

}

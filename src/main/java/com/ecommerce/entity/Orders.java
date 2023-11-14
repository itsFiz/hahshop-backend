package com.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String orderId;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	
	@ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

	private int quantity;
	
	private String orderTime;
	
	private String status;  // Processing, Delivered, On the Way // this will be for customer
	
	// delivery properties
	
	// updated by seller
	@ManyToOne
    @JoinColumn(name = "delivery_person_id")
    private User deliveryPerson;
	
	//updated by delivery person
	private String deliveryTime;  // Evening, Morning, Afternoon, Night
	
	private String deliveryDate; 
	
	private String deliveryStatus;  // Delivered, Pending // this will be for actual delivery status

}

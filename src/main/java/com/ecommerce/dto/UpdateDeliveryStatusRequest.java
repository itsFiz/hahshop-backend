package com.ecommerce.dto;

import lombok.Data;

@Data
public class UpdateDeliveryStatusRequest {
	
	private String orderId;
	
	private String deliveryStatus;
	
	private String deliveryTime;
	
	private String deliveryDate;
	
	private int deliveryId;  // for assigning Delivery Person for Order

}

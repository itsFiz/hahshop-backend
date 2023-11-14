package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.CommonApiResponse;
import com.ecommerce.dto.OrderResponseDto;
import com.ecommerce.dto.UpdateDeliveryStatusRequest;
import com.ecommerce.resource.OrderResource;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/order")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
	
	@Autowired
	private OrderResource orderResource;
	
	@PostMapping("/add")
	@Operation(summary = "Api to order the products in Cart")
	public ResponseEntity<CommonApiResponse> placeOrder(@RequestParam("userId") int userId) {
		return orderResource.orderProductsFromCart(userId);
	}
	
	@GetMapping("/fetch/all")
	@Operation(summary = "Api to fetch all orders")
	public ResponseEntity<OrderResponseDto> fetchAllOrders() {
		return orderResource.fetchAllOrders();
	}
	
	@GetMapping("/fetch/user-wise")
	@Operation(summary = "Api to fetch user orders")
	public ResponseEntity<OrderResponseDto> fetchUserOrders(@RequestParam("userId") int userId) {
		return orderResource.fetchUserOrders(userId);
	}
	
	@GetMapping("/fetch/seller-wise")
	@Operation(summary = "Api to fetch seller orders")
	public ResponseEntity<OrderResponseDto> fetchSellerOrders(@RequestParam("sellerId") int sellerId) {
		return orderResource.fetchSellerOrders(sellerId);
	}
	
	@GetMapping("/fetch")
	@Operation(summary = "Api to fetch orders by order id")
	public ResponseEntity<OrderResponseDto> fetchOrdersByOrderId(@RequestParam("orderId") String orderId) {
		return orderResource.fetchOrdersByOrderId(orderId);
	}
	
	@PutMapping("/assign/delivery-person")
	@Operation(summary = "Api to assign the Delivery Person for the Order")
	public ResponseEntity<OrderResponseDto> assignDeliveryPerson(@RequestBody UpdateDeliveryStatusRequest request) {
		return orderResource.assignDeliveryPersonForOrder(request);
	}
	
	@PutMapping("/update/delivery-status")
	@Operation(summary = "Api to update the delivery status of Order")
	public ResponseEntity<OrderResponseDto> updateDeliveryStatus(@RequestBody UpdateDeliveryStatusRequest request) {
		return orderResource.updateDeliveryStatus(request);
	}
	
	@GetMapping("/fetch/delivery-wise")
	@Operation(summary = "Api to fetch delivery person orders")
	public ResponseEntity<OrderResponseDto> fetchDeliveryOrders(@RequestParam("deliveryPersonId") int deliveryPersonId) {
		return orderResource.fetchDeliveryOrders(deliveryPersonId);
	}
	
	@GetMapping("/fetch/delivery-status/all")
	@Operation(summary = "Api to fetch all delivery status")
	public ResponseEntity<List<String>> fetchAllDeliveryStatus() {
		return orderResource.fetchAllDeliveryStatus();
	}
	
	@GetMapping("/fetch/delivery-time/all")
	@Operation(summary = "Api to fetch all delivery timings")
	public ResponseEntity<List<String>> fetchAllDeliveryTime() {
		return orderResource.fetchAllDeliveryTime();
	}

}

package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.CommonApiResponse;
import com.ecommerce.dto.RegisterUserRequestDto;
import com.ecommerce.dto.UserLoginRequest;
import com.ecommerce.dto.UserLoginResponse;
import com.ecommerce.dto.UserResponseDto;
import com.ecommerce.dto.UserStatusUpdateRequestDto;
import com.ecommerce.resource.UserResource;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired
	private UserResource userResource;

	// RegisterUserRequestDto, we will set only email, password & role from UI
	@PostMapping("/admin/register")
	@Operation(summary = "Api to register Admin")
	public ResponseEntity<CommonApiResponse> registerAdmin(@RequestBody RegisterUserRequestDto request) {
		return userResource.registerAdmin(request);
	}

	// for customer and seller register
	@PostMapping("register")
	@Operation(summary = "Api to register customer or seller user")
	public ResponseEntity<CommonApiResponse> registerUser(@RequestBody RegisterUserRequestDto request) {
		return this.userResource.registerUser(request);
	}
	
	@PostMapping("login")
	@Operation(summary =  "Api to login any User")
	public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
		return userResource.login(userLoginRequest);
	}
	
	@GetMapping("/fetch/role-wise")
	@Operation(summary =  "Api to get Users By Role")
	public ResponseEntity<UserResponseDto> fetchAllUsersByRole(@RequestParam("role") String role) throws JsonProcessingException {
		return userResource.getUsersByRole(role);
	}
	
	@GetMapping("/fetch/seller/delivery-person")
	@Operation(summary =  "Api to get Delivery persons by seller")
	public ResponseEntity<UserResponseDto> fetchDeliveryPerson(@RequestParam("sellerId") int sellerId) {
		return userResource.getDeliveryPersonsBySeller(sellerId);
	}
	
	@PutMapping("update/status")
	@Operation(summary =  "Api to update the user status")
	public ResponseEntity<CommonApiResponse> updateUserStatus(@RequestBody UserStatusUpdateRequestDto request) {
		return userResource.updateUserStatus(request);
	}
	
	@DeleteMapping("delete/seller")
	@Operation(summary =  "Api to update the user status")
	public ResponseEntity<CommonApiResponse> deleteSeller(@RequestParam("sellerId") int sellerId) {
		return userResource.deleteSeller(sellerId);
	}
	
	@DeleteMapping("delete/seller/delivery-person")
	@Operation(summary =  "Api to update the user status")
	public ResponseEntity<CommonApiResponse> deleteDeliveryPerson(@RequestParam("deliveryId") int deliveryId) {
		return userResource.deleteDeliveryPerson(deliveryId);
	}

}

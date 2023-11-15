package com.ecommerce.controller;

import com.ecommerce.Resources.CartResource;
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

import com.ecommerce.dto.CartRequestDto;
import com.ecommerce.dto.CartResponseDto;
import com.ecommerce.dto.CommonApiResponse;


@RestController
@RequestMapping("api/cart")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {

	@Autowired
	private CartResource cartResource;

	@PostMapping("/add")

	public ResponseEntity<CommonApiResponse> addCategory(@RequestBody CartRequestDto request) {
		return cartResource.addToCart(request);
	}
	
	@PutMapping("/update")

	public ResponseEntity<CartResponseDto> updateCart(@RequestBody CartRequestDto request) {
		return cartResource.updateCart(request);
	}
	
	@DeleteMapping("/delete")

	public ResponseEntity<CartResponseDto> deleteCart(@RequestBody CartRequestDto request) {
		return cartResource.deleteCart(request);
	}
	
	@GetMapping("/fetch")

	public ResponseEntity<CartResponseDto> fetchUserCart(@RequestParam("userId") int userId) {
		return cartResource.fetchUserCartDetails(userId);
	}

}

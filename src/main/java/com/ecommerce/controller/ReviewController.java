package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.AddReviewRequest;
import com.ecommerce.dto.CommonApiResponse;
import com.ecommerce.dto.ProductReviewResponseDto;
import com.ecommerce.resource.ReviewResource;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/product/review")
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {
	
	@Autowired
	private ReviewResource reviewResource;
	
	@PostMapping("add")
	@Operation(summary = "Api to add product review")
	public ResponseEntity<CommonApiResponse> addProductReview(@RequestBody AddReviewRequest review) {
		return this.reviewResource.addReview(review);
	}
	
	@GetMapping("fetch")
	@Operation(summary = "Api to fetch product reviews")
	public ResponseEntity<ProductReviewResponseDto> fetchProductReview(@RequestParam("productId") int productReview) {
		return this.reviewResource.fetchProductReviews(productReview);
	}
	
	@GetMapping("seller")
	@Operation(summary = "Api to fetch seller product review")
	public ResponseEntity<ProductReviewResponseDto> fetchSellerReviews(@RequestParam("sellerId") int sellerId) {
		return this.reviewResource.fetchSellerProductReviews(sellerId);
	}

}

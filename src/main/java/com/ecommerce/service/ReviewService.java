package com.ecommerce.service;

import java.util.List;

import com.ecommerce.entity.Product;
import com.ecommerce.entity.Review;
import com.ecommerce.entity.User;

public interface ReviewService {
	
	Review addReview(Review review);
	
	List<Review> fetchProductReviews(List<Product> products);

	List<Review> fetchSellerProductReview(User seller);
	
}

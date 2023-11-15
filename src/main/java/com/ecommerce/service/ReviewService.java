package com.ecommerce.service;

import java.util.List;

import com.ecommerce.Model.Product;
import com.ecommerce.Model.Review;
import com.ecommerce.Model.User;

public interface ReviewService {
	
	Review addReview(Review review);
	
	List<Review> fetchProductReviews(List<Product> products);

	List<Review> fetchSellerProductReview(User seller);
	
}

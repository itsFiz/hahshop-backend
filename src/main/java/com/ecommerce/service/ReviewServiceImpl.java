package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.Repositories.ReviewRepo;
import com.ecommerce.Model.Product;
import com.ecommerce.Model.Review;
import com.ecommerce.Model.User;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewRepo reviewRepo;

	@Override
	public Review addReview(Review review) {
		return reviewRepo.save(review);
	}

	@Override
	public List<Review> fetchProductReviews(List<Product> products) {
		return reviewRepo.findByProductIn(products);
	}

	@Override
	public List<Review> fetchSellerProductReview(User seller) {
		return reviewRepo.findAllOrdersBySeller(seller);
	}

}

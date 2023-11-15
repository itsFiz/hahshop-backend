package com.ecommerce.Repositories;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ecommerce.Model.Product;
import com.ecommerce.Model.Review;
import com.ecommerce.Model.User;

@Repository
@Service
public interface ReviewRepository extends JpaRepository<Review, Integer> {

	List<Review> findByProductIn(List<Product> products);

	@Query("SELECT r FROM Review r WHERE r.product.seller = :seller")
	List<Review> findAllOrdersBySeller(@Param("seller") User seller);

	default Review addReview(Review review) {
		return save(review);
	}

	default List<Review> fetchProductReviews(List<Product> products) {
		return findByProductIn(products);
	}

	default List<Review> fetchSellerProductReview(User seller) {
		return findAllOrdersBySeller(seller);
	}
}

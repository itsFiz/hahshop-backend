package com.ecommerce.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ecommerce.Model.Cart;
import com.ecommerce.Model.User;

@Repository
@Service
public interface CartRepository extends JpaRepository<Cart, Integer> {



	default List<Cart> addToCart(List<Cart> cart) {
		return saveAll(cart);
	}

	default Cart updateCart(Cart cart) {
		return save(cart);
	}

	default void deleteCart(Cart cart) {
		delete(cart);
	}

	default List<Cart> findByUser(User user) {
		return findByUser(user);
	}

	default Cart getCartById(int cartId) {
		Optional<Cart> optionalCart = findById(cartId);
		return optionalCart.orElse(null);
	}

	default void deleteCarts(List<Cart> carts) {
		deleteAll(carts);
	}
}

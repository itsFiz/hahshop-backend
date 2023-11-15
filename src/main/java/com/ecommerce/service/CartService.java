package com.ecommerce.service;

import java.util.List;

import com.ecommerce.Model.Cart;
import com.ecommerce.Model.User;

public interface CartService {

	List<Cart> addToCart(List<Cart> cart);

	Cart updateCart(Cart cart);

	void deleteCart(Cart cart);

	List<Cart> findByUser(User user);

	Cart getCartById(int cartId);
	
	void deleteCarts(List<Cart> cart);

}

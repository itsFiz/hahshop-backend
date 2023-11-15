package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.Repositories.CartRepo;
import com.ecommerce.Model.Cart;
import com.ecommerce.Model.User;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepo cartRepo;

	@Override
	public List<Cart> addToCart(List<Cart> cart) {
		return cartRepo.saveAll(cart);
	}

	@Override
	public Cart updateCart(Cart cart) {
		return cartRepo.save(cart);
	}

	@Override
	public void deleteCart(Cart cart) {
		cartRepo.delete(cart);
	}

	@Override
	public List<Cart> findByUser(User user) {
		return cartRepo.findByUser(user);
	}

	@Override
	public Cart getCartById(int cartId) {
		
		Optional<Cart> optionalCart = this.cartRepo.findById(cartId);
		
		if(optionalCart.isPresent()) {
			return optionalCart.get();
		}
		
		else {
			return null;
		}
	}

	@Override
	public void deleteCarts(List<Cart> carts) {
		this.cartRepo.deleteAll(carts);
	}

}

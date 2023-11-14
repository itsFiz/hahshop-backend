package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.CartDao;
import com.ecommerce.entity.Cart;
import com.ecommerce.entity.User;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartDao cartDao;

	@Override
	public List<Cart> addToCart(List<Cart> cart) {
		return cartDao.saveAll(cart);
	}

	@Override
	public Cart updateCart(Cart cart) {
		return cartDao.save(cart);
	}

	@Override
	public void deleteCart(Cart cart) {
		cartDao.delete(cart);
	}

	@Override
	public List<Cart> findByUser(User user) {
		return cartDao.findByUser(user);
	}

	@Override
	public Cart getCartById(int cartId) {
		
		Optional<Cart> optionalCart = this.cartDao.findById(cartId);
		
		if(optionalCart.isPresent()) {
			return optionalCart.get();
		}
		
		else {
			return null;
		}
	}

	@Override
	public void deleteCarts(List<Cart> carts) {
		this.cartDao.deleteAll(carts);
	}

}

package com.ecommerce.service;


import com.ecommerce.Model.Cart;
import com.ecommerce.Model.User;
import com.ecommerce.Repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepository cartDao;

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

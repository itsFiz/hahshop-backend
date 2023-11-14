package com.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.User;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {

	List<Cart> findByUser(User user);

}

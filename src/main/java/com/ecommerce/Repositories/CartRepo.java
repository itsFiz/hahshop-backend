package com.ecommerce.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.Model.Cart;
import com.ecommerce.Model.User;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {

	List<Cart> findByUser(User user);

}

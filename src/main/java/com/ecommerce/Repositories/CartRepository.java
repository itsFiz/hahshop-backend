package com.ecommerce.Repositories;


import java.util.List;

import com.ecommerce.Model.Cart;
import com.ecommerce.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	List<Cart> findByUser(User user);

}

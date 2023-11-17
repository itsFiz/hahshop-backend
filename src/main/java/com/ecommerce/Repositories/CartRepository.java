package com.ecommerce.Repositories;


import com.ecommerce.Model.Cart;
import com.ecommerce.Model.User;
import com.ecommerce.Repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
@Service
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByUser(User user);
}

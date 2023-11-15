package com.ecommerce.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.Model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	User findByEmailId(String email);

	User findByEmailIdAndStatus(String email, String status);

	User findByRoleAndStatusIn(String role, List<String> status);

	List<User> findByRole(String role);

	List<User> findBySellerAndRole(User seller, String role);
	
	List<User> findBySellerAndRoleAndStatusIn(User seller, String role, List<String> status);
	
	User findByEmailIdAndRoleAndStatus(String emailId, String role, String status);

	
	List<User> findByRoleAndStatus(String role, String status);

}

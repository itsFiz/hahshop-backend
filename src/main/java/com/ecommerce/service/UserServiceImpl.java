package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.Repositories.UserRepo;
import com.ecommerce.Model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public User addUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public User updateUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public User getUserByEmailAndStatus(String emailId, String status) {
		return userRepo.findByEmailIdAndStatus(emailId, status);
	}

	@Override
	public User getUserByEmailid(String emailId) {
		return userRepo.findByEmailId(emailId);
	}

	@Override
	public List<User> getUserByRole(String role) {
		return userRepo.findByRole(role);
	}

	@Override
	public User getUserById(int userId) {

		Optional<User> optionalUser = this.userRepo.findById(userId);

		if (optionalUser.isPresent()) {
			return optionalUser.get();
		} else {
			return null;
		}

	}
	
	@Override
	public User getUserByEmailIdAndRoleAndStatus(String emailId, String role, String status) {
		return this.userRepo.findByEmailIdAndRoleAndStatus(emailId, role, status);
	}

	@Override
	public List<User> getUserBySellerAndRoleAndStatusIn(User seller, String role, List<String> status) {
		return this.userRepo.findBySellerAndRoleAndStatusIn(seller, role, status);
	}

	@Override
	public List<User> updateAllUser(List<User> users) {
		return this.userRepo.saveAll(users);
	}

	@Override
	public List<User> getUserByRoleAndStatus(String role, String status) {
		return this.userRepo.findByRoleAndStatus(role, status);
	}
	
}

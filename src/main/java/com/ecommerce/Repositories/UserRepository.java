package com.ecommerce.Repositories;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.ecommerce.Model.User;

@Repository
@Service
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmailId(String email);

	User findByEmailIdAndStatus(String email, String status);

	User findByRoleAndStatusIn(String role, List<String> status);

	List<User> findByRole(String role);

	List<User> findBySellerAndRole(User seller, String role);

	List<User> findBySellerAndRoleAndStatusIn(User seller, String role, List<String> status);

	User findByEmailIdAndRoleAndStatus(String emailId, String role, String status);

	List<User> findByRoleAndStatus(String role, String status);

	default User addUser(User user) {
		return save(user);
	}

	default User updateUser(User user) {
		return save(user);
	}

	default User getUserByEmailAndStatus(String emailId, String status) {
		return findByEmailIdAndStatus(emailId, status);
	}

	default User getUserByEmailId(String emailId) {
		return findByEmailId(emailId);
	}

	default List<User> getUserByRole(String role) {
		return findByRole(role);
	}

	default User getUserById(int userId) {
		Optional<User> optionalUser = findById(userId);
		return optionalUser.orElse(null);
	}

	default User getUserByEmailIdAndRoleAndStatus(String emailId, String role, String status) {
		return findByEmailIdAndRoleAndStatus(emailId, role, status);
	}

	default List<User> getUserBySellerAndRoleAndStatusIn(User seller, String role, List<String> status) {
		return findBySellerAndRoleAndStatusIn(seller, role, status);
	}

	default List<User> updateAllUser(List<User> users) {
		return saveAll(users);
	}

	default List<User> getUserByRoleAndStatus(String role, String status) {
		return findByRoleAndStatus(role, status);
	}
}

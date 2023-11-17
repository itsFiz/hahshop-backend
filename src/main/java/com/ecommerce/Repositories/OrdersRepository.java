package com.ecommerce.Repositories;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ecommerce.Model.Orders;
import com.ecommerce.Model.User;

@Repository
@Service
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

	List<Orders> findByOrderId(String orderId);

	List<Orders> findByOrderIdAndStatusIn(String orderId, List<String> status);

	List<Orders> findByUser(User user);

	List<Orders> findByUserAndStatusIn(User user, List<String> status);

	@Query("SELECT o FROM Orders o WHERE o.product.seller = :seller and status In (:status)")
	List<Orders> findAllOrdersBySellerAndStatusIn(@Param("seller") User seller, @Param("status") List<String> status);

	@Query("SELECT o FROM Orders o WHERE o.product.seller = :seller And status In (:status) AND o.orderTime BETWEEN :startDate AND :endDate")
	List<Orders> findBySellerAndStatusAndOrderTime(@Param("seller") User seller, @Param("status") List<String> status,
												   @Param("startDate") String startDate, @Param("endDate") String endDate);

	@Query("SELECT o FROM Orders o WHERE status In (:status) AND o.orderTime BETWEEN :startDate AND :endDate")
	List<Orders> findByStatusAndOrderTime(@Param("status") List<String> status, @Param("startDate") String startDate,
										  @Param("endDate") String endDate);

	@Query("SELECT o FROM Orders o WHERE status In (:status) AND o.deliveryPerson = :deliveryPerson")
	List<Orders> findByStatusAndDeliveryPerson(@Param("status") List<String> status, @Param("deliveryPerson") User deliveryPerson);

	default List<Orders> addOrder(List<Orders> orders) {
		return saveAll(orders);
	}

	default List<Orders> updateOrders(List<Orders> orders) {
		return saveAll(orders);
	}

	default List<Orders> getOrdersByOrderId(String orderId) {
		return findByOrderId(orderId);
	}

	default Orders getOrderById(int orderId) {
		return findById(orderId).orElse(null);
	}

	default List<Orders> getOrdersByUser(User user) {
		return findByUser(user);
	}

	default Orders updateOrder(Orders order) {
		return save(order);
	}

	default List<Orders> getOrdersByOrderIdAndStatusIn(String orderId, List<String> status) {
		return findByOrderIdAndStatusIn(orderId, status);
	}

	default List<Orders> getOrdersByUserAndStatusIn(User user, List<String> status) {
		return findByUserAndStatusIn(user, status);
	}

	default List<Orders> getOrdersBySellerAndStatusIn(User seller, List<String> status) {
		return findAllOrdersBySellerAndStatusIn(seller, status);
	}

	default List<Orders> getAllOrders() {
		return findAll();
	}

	default List<Orders> getOrdersByDeliveryPersonAndStatusIn(User deliveryPerson, List<String> status) {
		return findByStatusAndDeliveryPerson(status, deliveryPerson);
	}
}

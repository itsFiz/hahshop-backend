package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.OrdersDao;
import com.ecommerce.entity.Orders;
import com.ecommerce.entity.User;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrdersDao orderDao;

	@Override
	public List<Orders> addOrder(List<Orders> orders) {
		return this.orderDao.saveAll(orders);
	}

	@Override
	public List<Orders> updateOrders(List<Orders> orders) {
		return this.orderDao.saveAll(orders);
	}

	@Override
	public List<Orders> getOrdersByOrderId(String orderId) {

		return this.orderDao.findByOrderId(orderId);

	}

	@Override
	public Orders getOrderById(int orderId) {

		Optional<Orders> optionalOrder = this.orderDao.findById(orderId);

		if (optionalOrder.isPresent()) {
			return optionalOrder.get();
		} else {
			return null;
		}

	}

	@Override
	public List<Orders> getOrdersByUser(User user) {
		return orderDao.findByUser(user);
	}

	@Override
	public Orders updateOrder(Orders order) {
		return orderDao.save(order);
	}

	@Override
	public List<Orders> getOrdersByOrderIdAndStatusIn(String orderId, List<String> status) {
		return this.orderDao.findByOrderIdAndStatusIn(orderId, status);
	}

	@Override
	public List<Orders> getOrdersByUserAndStatusIn(User user, List<String> status) {
		return this.orderDao.findByUserAndStatusIn(user, status);
	}

	@Override
	public List<Orders> getOrdersBySellerAndStatusIn(User user, List<String> status) {
		return this.orderDao.findAllOrdersBySellerAndStatusIn(user, status);
	}

	@Override
	public List<Orders> getAllOrders() {
		return this.orderDao.findAll();
	}

	@Override
	public List<Orders> getOrdersByDeliveryPersonAndStatusIn(User user, List<String> status) {
		return this.orderDao.findByStatusAndDeliveryPerson(status, user);
	}

}

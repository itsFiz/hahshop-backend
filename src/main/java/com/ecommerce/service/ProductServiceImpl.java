package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public Product addProduct(Product product) {
		return productDao.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return productDao.save(product);
	}

	@Override
	public Product getProductById(int productId) {

		Optional<Product> optionalProduct = productDao.findById(productId);

		if (optionalProduct.isPresent()) {
			return optionalProduct.get();
		} else {
			return null;
		}

	}

	@Override
	public List<Product> getAllProductByStatusIn(List<String> status) {
		return this.productDao.findByStatusIn(status);
	}

	@Override
	public Long countByStatusIn(List<String> status) {
		return this.productDao.countByStatusIn(status);
	}

	@Override
	public Long countByStatusInAndSeller(List<String> status, User seller) {
		return this.productDao.countByStatusInAndSeller(status, seller);
	}

	@Override
	public List<Product> getAllProductBySellerAndStatusIn(User Seller, List<String> status) {
		return this.productDao.findBySellerAndStatusIn(Seller, status);
	}

	@Override
	public List<Product> getAllProductBySellerAndCategoryAndStatusIn(User seller, Category category,
			List<String> status) {
		return this.productDao.findBySellerAndCategoryAndAndStatusIn(seller, category, status);
	}

	@Override
	public List<Product> updateAllProduct(List<Product> products) {
		return this.productDao.saveAll(products);
	}

	@Override
	public List<Product> getAllProductByCategoryAndStatusIn(Category category, List<String> status) {
		return this.productDao.findByCategoryAndStatusIn(category, status);
	}

	@Override
	public List<Product> searchProductNameAndStatusIn(String productName, List<String> status) {

		return this.productDao.findByNameContainingIgnoreCaseAndStatusIn(productName, status);
	}

	@Override
	public List<Product> searchProductNameAndSellerAndStatusIn(String productName, User seller, List<String> status) {
		return this.productDao.findByNameContainingIgnoreCaseAndSellerAndStatusIn(productName, seller, status);
	}

}

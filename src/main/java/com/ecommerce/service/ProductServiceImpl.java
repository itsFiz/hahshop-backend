package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.ecommerce.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ecommerce.Model.Category;
import com.ecommerce.Model.Product;
import com.ecommerce.Model.User;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product getProductById(int productId) {

		Optional<Product> optionalProduct = productRepository.findById(productId);

		if (optionalProduct.isPresent()) {
			return optionalProduct.get();
		} else {
			return null;
		}

	}

	@Override
	public List<Product> getAllProductByStatusIn(List<String> status) {
		return this.productRepository.findByStatusIn(status);
	}

	@Override
	public Long countByStatusIn(List<String> status) {
		return this.productRepository.countByStatusIn(status);
	}

	@Override
	public Long countByStatusInAndSeller(List<String> status, User seller) {
		return this.productRepository.countByStatusInAndSeller(status, seller);
	}

	@Override
	public List<Product> getAllProductBySellerAndStatusIn(User Seller, List<String> status) {
		return this.productRepository.findBySellerAndStatusIn(Seller, status);
	}

	@Override
	public List<Product> getAllProductBySellerAndCategoryAndStatusIn(User seller, Category category,
			List<String> status) {
		return this.productRepository.findBySellerAndCategoryAndAndStatusIn(seller, category, status);
	}

	@Override
	public List<Product> updateAllProduct(List<Product> products) {
		return this.productRepository.saveAll(products);
	}

	@Override
	public List<Product> getAllProductByCategoryAndStatusIn(Category category, List<String> status) {
		return this.productRepository.findByCategoryAndStatusIn(category, status);
	}

	@Override
	public List<Product> searchProductNameAndStatusIn(String productName, List<String> status) {

		return this.productRepository.findByNameContainingIgnoreCaseAndStatusIn(productName, status);
	}

	@Override
	public List<Product> searchProductNameAndSellerAndStatusIn(String productName, User seller, List<String> status) {
		return this.productRepository.findByNameContainingIgnoreCaseAndSellerAndStatusIn(productName, seller, status);
	}

}

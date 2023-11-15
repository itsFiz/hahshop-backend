package com.ecommerce.Repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ecommerce.Model.Category;
import com.ecommerce.Model.Product;
import com.ecommerce.Model.User;

@Repository
@Service
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByStatusIn(List<String> status);

    List<Product> findBySellerAndStatusIn(User seller, List<String> status);

    List<Product> findBySellerAndCategoryAndAndStatusIn(User seller, Category category, List<String> status);

    List<Product> findByCategoryAndStatusIn(Category category, List<String> status);


    List<Product> findByNameContainingIgnoreCaseAndStatusIn(String productName, List<String> status);

    List<Product> findByNameContainingIgnoreCaseAndSellerAndStatusIn(String productName, User seller, List<String> status);

    default Product addProduct(Product product) {
        return save(product);
    }

    default Product updateProduct(Product product) {
        return save(product);
    }

    default Product getProductById(int productId) {
        Optional<Product> optionalProduct = findById(productId);
        return optionalProduct.orElse(null);
    }

    default List<Product> getAllProductByStatusIn(List<String> status) {
        return findByStatusIn(status);
    }

    default Long countByStatusIn(List<String> status) {
        return countByStatusIn(status);
    }

    default Long countByStatusInAndSeller(List<String> status, User seller) {
        return countByStatusInAndSeller(status, seller);
    }

    default List<Product> getAllProductBySellerAndStatusIn(User seller, List<String> status) {
        return findBySellerAndStatusIn(seller, status);
    }

    default List<Product> getAllProductBySellerAndCategoryAndStatusIn(User seller, Category category, List<String> status) {
        return findBySellerAndCategoryAndAndStatusIn(seller, category, status);
    }

    default List<Product> updateAllProduct(List<Product> products) {
        return saveAll(products);
    }

    default List<Product> getAllProductByCategoryAndStatusIn(Category category, List<String> status) {
        return findByCategoryAndStatusIn(category, status);
    }

    default List<Product> searchProductNameAndStatusIn(String productName, List<String> status) {
        return findByNameContainingIgnoreCaseAndStatusIn(productName, status);
    }

    default List<Product> searchProductNameAndSellerAndStatusIn(String productName, User seller, List<String> status) {
        return findByNameContainingIgnoreCaseAndSellerAndStatusIn(productName, seller, status);
    }
}

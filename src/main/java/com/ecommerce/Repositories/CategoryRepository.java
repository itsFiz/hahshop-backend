package com.ecommerce.Repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ecommerce.Model.Category;

@Repository
@Service
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	List<Category> findByStatusIn(List<String> status);

	Long countByStatusIn(List<String> status);

	default Category addCategory(Category category) {
		return save(category);
	}

	default Category updateCategory(Category category) {
		return save(category);
	}

	default Category getCategoryById(int categoryId) {
		Optional<Category> optionalCategory = findById(categoryId);
		return optionalCategory.orElse(null);
	}

	default List<Category> getCategoriesByStatusIn(List<String> status) {
		return findByStatusIn(status);
	}
}

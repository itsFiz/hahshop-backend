package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.Repositories.CategoryRepo;
import com.ecommerce.Model.Category;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public Category addCategory(Category category) {
		return this.categoryRepo.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		return this.categoryRepo.save(category);
	}

	@Override
	public Category getCategoryById(int categoryId) {

		Optional<Category> optionalCategory = this.categoryRepo.findById(categoryId);

		if (optionalCategory.isPresent()) {
			return optionalCategory.get();
		} else {
			return null;
		}

	}

	@Override
	public List<Category> getCategoriesByStatusIn(List<String> status) {
		return this.categoryRepo.findByStatusIn(status);
	}

}

package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.CategoryResponseDto;
import com.ecommerce.dto.CommonApiResponse;
import com.ecommerce.entity.Category;
import com.ecommerce.resource.CategoryResource;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/category")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {
	
	@Autowired
	private CategoryResource categoryResource;
	
	@PostMapping("/add")
	@Operation(summary = "Api to add category")
	public ResponseEntity<CommonApiResponse> addCategory(@RequestBody Category category) {
		return categoryResource.addCategory(category);
	}
	
	@PutMapping("/update")
	@Operation(summary = "Api to update category")
	public ResponseEntity<CommonApiResponse> updateCategory(@RequestBody Category category) {
		return categoryResource.updateCategory(category);
	}
	
	@GetMapping("/fetch/all")
	@Operation(summary = "Api to fetch all category")
	public ResponseEntity<CategoryResponseDto> fetchAllCategory() {
		return categoryResource.fetchAllCategory();
	}
	
	@DeleteMapping("/delete")
	@Operation(summary = "Api to delete category all its products")
	public ResponseEntity<CommonApiResponse> deleteCategory(@RequestParam("categoryId") int categoryId) {
		return categoryResource.deleteCategory(categoryId);
	}

}

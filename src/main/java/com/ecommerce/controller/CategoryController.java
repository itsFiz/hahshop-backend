package com.ecommerce.controller;

import com.ecommerce.Model.Product;
import com.ecommerce.exception.CategorySaveFailedException;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.ProductService;
import com.ecommerce.utility.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
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
import com.ecommerce.Model.Category;
import com.ecommerce.resource.CategoryResource;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/category")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {
	private final Logger LOG = LoggerFactory.getLogger(CategoryResource.class);
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	
	@PostMapping("/add")

	public ResponseEntity<CommonApiResponse> addCategory(@RequestBody Category category) {
		LOG.info("Request received for add category");

		CommonApiResponse response = new CommonApiResponse();

		if (category == null) {
			response.setResponseMessage("missing input");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		category.setStatus(Constants.CategoryStatus.ACTIVE.value());

		Category savedCategory = this.categoryService.addCategory(category);

		if (savedCategory == null) {
			throw new CategorySaveFailedException("Failed to add category");
		}

		response.setResponseMessage("Category Added Successful");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
	}
	
	@PutMapping("/update")

	public ResponseEntity<CommonApiResponse> updateCategory(@RequestBody Category category) {

		LOG.info("Request received for add category");

		CommonApiResponse response = new CommonApiResponse();

		if (category == null) {
			response.setResponseMessage("missing input");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		if (category.getId() == 0) {
			response.setResponseMessage("missing category Id");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		category.setStatus(Constants.CategoryStatus.ACTIVE.value());
		Category savedCategory = this.categoryService.updateCategory(category);

		if (savedCategory == null) {
			throw new CategorySaveFailedException("Failed to update category");
		}

		response.setResponseMessage("Category Updated Successful");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
	}
	
	@GetMapping("/fetch/all")
	public ResponseEntity<CategoryResponseDto> fetchAllCategory() {

		LOG.info("Request received for fetching all categories");

		CategoryResponseDto response = new CategoryResponseDto();

		List<Category> categories = new ArrayList<>();

		categories = this.categoryService.getCategoriesByStatusIn(Arrays.asList(Constants.CategoryStatus.ACTIVE.value()));

		if (CollectionUtils.isEmpty(categories)) {
			response.setResponseMessage("No Categories found");
			response.setSuccess(false);

			return new ResponseEntity<CategoryResponseDto>(response, HttpStatus.OK);
		}

		response.setCategories(categories);
		response.setResponseMessage("Category fetched successful");
		response.setSuccess(true);

		return new ResponseEntity<CategoryResponseDto>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<CommonApiResponse> deleteCategory(@RequestParam("categoryId") int categoryId) {

		LOG.info("Request received for deleting category");

		CommonApiResponse response = new CommonApiResponse();

		if (categoryId == 0) {
			response.setResponseMessage("missing category Id");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		Category category = this.categoryService.getCategoryById(categoryId);

		if (category == null) {
			response.setResponseMessage("category not found");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		List<Product> products = this.productService.getAllProductByCategoryAndStatusIn(category,
				Arrays.asList(Constants.ProductStatus.ACTIVE.value()));

		category.setStatus(Constants.CategoryStatus.DEACTIVATED.value());
		Category updatedCategory = this.categoryService.updateCategory(category);

		if (updatedCategory == null) {
			throw new CategorySaveFailedException("Failed to delete the Category");
		}

		if (!CollectionUtils.isEmpty(products)) {

			for (Product product : products) {
				product.setStatus(Constants.ProductStatus.DEACTIVATED.value());
			}

			List<Product> udpatedProducts = this.productService.updateAllProduct(products);

			if (CollectionUtils.isEmpty(udpatedProducts)) {
				throw new CategorySaveFailedException("Failed to delete the Category");
			}

		}

		response.setResponseMessage("Category Deleted Successful");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
	}

}

package com.ecommerce.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ecommerce.dto.CategoryResponseDto;
import com.ecommerce.dto.CommonApiResponse;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;
import com.ecommerce.exception.CategorySaveFailedException;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.ProductService;
import com.ecommerce.utility.Constants.CategoryStatus;
import com.ecommerce.utility.Constants.ProductStatus;

import jakarta.transaction.Transactional;

@Component
@Transactional
public class CategoryResource {

	private final Logger LOG = LoggerFactory.getLogger(CategoryResource.class);

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	public ResponseEntity<CommonApiResponse> addCategory(Category category) {
		
		LOG.info("Request received for add category");

		CommonApiResponse response = new CommonApiResponse();

		if (category == null) {
			response.setResponseMessage("missing input");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		category.setStatus(CategoryStatus.ACTIVE.value());

		Category savedCategory = this.categoryService.addCategory(category);

		if (savedCategory == null) {
			throw new CategorySaveFailedException("Failed to add category");
		}

		response.setResponseMessage("Category Added Successful");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);

	}

	public ResponseEntity<CommonApiResponse> updateCategory(Category category) {
		
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

		category.setStatus(CategoryStatus.ACTIVE.value());
		Category savedCategory = this.categoryService.updateCategory(category);

		if (savedCategory == null) {
			throw new CategorySaveFailedException("Failed to update category");
		}

		response.setResponseMessage("Category Updated Successful");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);

	}

	public ResponseEntity<CategoryResponseDto> fetchAllCategory() {

		LOG.info("Request received for fetching all categories");

		CategoryResponseDto response = new CategoryResponseDto();

		List<Category> categories = new ArrayList<>();

		categories = this.categoryService.getCategoriesByStatusIn(Arrays.asList(CategoryStatus.ACTIVE.value()));

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

	public ResponseEntity<CommonApiResponse> deleteCategory(int categoryId) {

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
				Arrays.asList(ProductStatus.ACTIVE.value()));

		category.setStatus(CategoryStatus.DEACTIVATED.value());
		Category updatedCategory = this.categoryService.updateCategory(category);

		if (updatedCategory == null) {
			throw new CategorySaveFailedException("Failed to delete the Category");
		}

		if (!CollectionUtils.isEmpty(products)) {

			for (Product product : products) {
				product.setStatus(ProductStatus.DEACTIVATED.value());
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

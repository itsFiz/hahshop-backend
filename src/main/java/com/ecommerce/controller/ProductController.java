package com.ecommerce.controller;

<<<<<<< HEAD
import com.ecommerce.Model.Category;
import com.ecommerce.Model.Product;
import com.ecommerce.Model.User;
import com.ecommerce.dto.*;
import com.ecommerce.exception.ProductSaveFailedException;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.StorageService;
import com.ecommerce.service.UserService;
import com.ecommerce.utility.Constants;
import jakarta.servlet.ServletOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
=======
import com.ecommerce.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> e95eaa6831f173772dd3d5b1a0c10f53122644c8
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.resource.ProductResource;

<<<<<<< HEAD

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
=======
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
>>>>>>> e95eaa6831f173772dd3d5b1a0c10f53122644c8

@RestController
@RequestMapping("api/product")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

	private final Logger LOG = LoggerFactory.getLogger(ProductResource.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;

	@Autowired
	private StorageService storageService;


	@PostMapping("add")
	public ResponseEntity<CommonApiResponse> addProduct(ProductAddRequest productDto) {
		LOG.info("request received for Product add");

		CommonApiResponse response = new CommonApiResponse();

		if (productDto == null || productDto.getCategoryId() == 0 || productDto.getSellerId() == 0) {
			response.setResponseMessage("missing input");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		Product product = ProductAddRequest.toEntity(productDto);
		product.setStatus(Constants.ProductStatus.ACTIVE.value());

		User seller = this.userService.getUserById(productDto.getSellerId());

		if (seller == null) {
			response.setResponseMessage("Seller not found");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		Category category = this.categoryService.getCategoryById(productDto.getCategoryId());

		if (category == null) {
			response.setResponseMessage("Category not found");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		product.setSeller(seller);
		product.setCategory(category);

		// store product image in Image Folder and give name to store in database
		String productImageName1 = storageService.store(productDto.getImage1());
		String productImageName2 = storageService.store(productDto.getImage2());
		String productImageName3 = storageService.store(productDto.getImage3());

		product.setImage1(productImageName1);
		product.setImage2(productImageName2);
		product.setImage3(productImageName3);

		Product savedProduct = this.productService.addProduct(product);

		if (savedProduct == null) {
			throw new ProductSaveFailedException("Failed to save the Product");
		}

		response.setResponseMessage("Product added successful");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
	}

	@PutMapping("update/detail")
	public ResponseEntity<CommonApiResponse> updateProductDetails(@RequestBody ProductDetailUpdateRequest request) {
		System.out.println(request);
		LOG.info("request received for update product");

		CommonApiResponse response = new CommonApiResponse();

		if (request == null) {
			response.setResponseMessage("missing input");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		Product product = this.productService.getProductById(request.getId());

		if (product == null) {
			response.setResponseMessage("product not found");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// it will update the category if changed
		if (product.getCategory().getId() != request.getCategoryId()) {
			Category category = this.categoryService.getCategoryById(request.getCategoryId());
			product.setCategory(category);
		}

		product.setDescription(request.getDescription());
		product.setName(request.getName());
		product.setPrice(request.getPrice());
		product.setQuantity(request.getQuantity());

		Product updatedProduct = this.productService.updateProduct(product);

		if (updatedProduct == null) {
			throw new ProductSaveFailedException("Failed to update the Product details");
		}

		response.setResponseMessage("Product added successful");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
	}

	@PutMapping("update/image")
	public ResponseEntity<CommonApiResponse> updateProductDetails(ProductAddRequest request) {

		LOG.info("request received for update product images");

		CommonApiResponse response = new CommonApiResponse();

		if (request == null || request.getId() == 0) {
			response.setResponseMessage("missing input");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		if (request.getImage1() == null || request.getImage2() == null || request.getImage3() == null) {
			response.setResponseMessage("Image not selected");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		Product product = this.productService.getProductById(request.getId());

		String existingImage1 = product.getImage1();
		String existingImage2 = product.getImage2();
		String existingImage3 = product.getImage3();

		// store updated product image in Image Folder and give name to store in
		// database
		String productImageName1 = storageService.store(request.getImage1());
		String productImageName2 = storageService.store(request.getImage2());
		String productImageName3 = storageService.store(request.getImage3());

		product.setImage1(productImageName1);
		product.setImage2(productImageName2);
		product.setImage3(productImageName3);

		Product updatedProduct = this.productService.addProduct(product);

		if (updatedProduct == null) {
			throw new ProductSaveFailedException("Failed to update the Product image");
		}

		// deleting the existing image from the folder
		try {
			this.storageService.delete(existingImage1);
			this.storageService.delete(existingImage2);
			this.storageService.delete(existingImage3);

		} catch (Exception e) {
			LOG.error("Exception Caught: " + e.getMessage());

			throw new ProductSaveFailedException("Failed to update the Product image");
		}

		response.setResponseMessage("Product Image Updated Successful");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);

	}

	@DeleteMapping("delete")
	public ResponseEntity<CommonApiResponse> deleteProduct(@RequestParam("productId") int productId,
			@RequestParam("sellerId") int sellerId) {

		LOG.info("request received for deleting the product");

		CommonApiResponse response = new CommonApiResponse();

		if (productId == 0 || sellerId == 0) {
			response.setResponseMessage("missing input");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		Product product = this.productService.getProductById(productId);

		if (product == null) {
			response.setResponseMessage("product not found, failed to delete the product");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (product.getSeller().getId() != sellerId) {
			response.setResponseMessage("Product not owned by Seller, Can't Delete");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		product.setStatus(Constants.ProductStatus.DEACTIVATED.value());

		Product deletedProduct = this.productService.updateProduct(product);

		if (deletedProduct == null) {
			throw new ProductSaveFailedException("Failed to delete the Product");
		}

		response.setResponseMessage("Product Deleted Successful");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
	}

	@GetMapping("fetch")
	public ResponseEntity<ProductResponseDto> fetchProductById(@RequestParam(name = "productId") int productId) {
		LOG.info("request received for searching the seller products");

		ProductResponseDto response = new ProductResponseDto();

		if (productId == 0) {
			response.setResponseMessage("missing product id");
			response.setSuccess(false);

			return new ResponseEntity<ProductResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		Product product = this.productService.getProductById(productId);

		if (product == null) {
			response.setResponseMessage("Product not found");
			response.setSuccess(false);

			return new ResponseEntity<ProductResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		response.setProducts(Arrays.asList(product));
		response.setResponseMessage("Product fetched success");
		response.setSuccess(true);

		return new ResponseEntity<ProductResponseDto>(response, HttpStatus.OK);
	}

	@GetMapping("fetch/all")
		public ResponseEntity<ProductResponseDto> fetchAllProducts() {

			LOG.info("request received for fetching all the products");

			ProductResponseDto response = new ProductResponseDto();

			List<Product> products = this.productService
					.getAllProductByStatusIn(Arrays.asList(Constants.ProductStatus.ACTIVE.value()));

			if (CollectionUtils.isEmpty(products)) {
				response.setResponseMessage("No products found");
				response.setSuccess(false);

				return new ResponseEntity<ProductResponseDto>(response, HttpStatus.OK);
			}

			response.setProducts(products);
			response.setResponseMessage("Product fetched success");
			response.setSuccess(true);

			return new ResponseEntity<ProductResponseDto>(response, HttpStatus.OK);
		}

	@GetMapping("fetch/seller-wise")
	public ResponseEntity<ProductResponseDto> fetchAllSellerProduct(@RequestParam(name = "sellerId") int sellerId) {

		LOG.info("request received for fetching all the Seller products");

		ProductResponseDto response = new ProductResponseDto();

		if (sellerId == 0) {
			response.setResponseMessage("Seller not found");
			response.setSuccess(false);

			return new ResponseEntity<ProductResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		User seller = this.userService.getUserById(sellerId);

		if (seller == null) {
			response.setResponseMessage("Seller not found");
			response.setSuccess(false);

			return new ResponseEntity<ProductResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		List<Product> products = this.productService.getAllProductBySellerAndStatusIn(seller,
				Arrays.asList(Constants.ProductStatus.ACTIVE.value()));

		if (CollectionUtils.isEmpty(products)) {
			response.setResponseMessage("No products found");
			response.setSuccess(false);

			return new ResponseEntity<ProductResponseDto>(response, HttpStatus.OK);
		}

		response.setProducts(products);
		response.setResponseMessage("Product fetched success");
		response.setSuccess(true);

		return new ResponseEntity<ProductResponseDto>(response, HttpStatus.OK);
	}

	@GetMapping("fetch/seller-wise/category-wise")
	public ResponseEntity<ProductResponseDto> fetchAllSellerProductAndCategory(
			@RequestParam(name = "sellerId") int sellerId, @RequestParam(name = "categoryId") int categoryId) {
		LOG.info("request received for fetching all the Seller products category wise");

		ProductResponseDto response = new ProductResponseDto();

		if (sellerId == 0 || categoryId == 0) {
			response.setResponseMessage("missing input");
			response.setSuccess(false);

			return new ResponseEntity<ProductResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		User seller = this.userService.getUserById(sellerId);

		if (seller == null) {
			response.setResponseMessage("Seller not found");
			response.setSuccess(false);

			return new ResponseEntity<ProductResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		Category category = this.categoryService.getCategoryById(categoryId);

		if (category == null) {
			response.setResponseMessage("category not found");
			response.setSuccess(false);

			return new ResponseEntity<ProductResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		List<Product> products = this.productService.getAllProductBySellerAndCategoryAndStatusIn(seller, category,
				Arrays.asList(Constants.ProductStatus.ACTIVE.value()));

		if (CollectionUtils.isEmpty(products)) {
			response.setResponseMessage("No products found");
			response.setSuccess(false);

			return new ResponseEntity<ProductResponseDto>(response, HttpStatus.OK);
		}

		response.setProducts(products);
		response.setResponseMessage("Product fetched success");
		response.setSuccess(true);

		return new ResponseEntity<ProductResponseDto>(response, HttpStatus.OK);
	}

	@GetMapping("search")
	public ResponseEntity<ProductResponseDto> searchProductsByName(
			@RequestParam(name = "productName") String productName) {
		LOG.info("request received for searching the products");

		ProductResponseDto response = new ProductResponseDto();

		if (productName == null) {
			response.setResponseMessage("missing input, product name");
			response.setSuccess(false);

			return new ResponseEntity<ProductResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		List<Product> products = this.productService.searchProductNameAndStatusIn(productName,
				Arrays.asList(Constants.ProductStatus.ACTIVE.value()));

		if (CollectionUtils.isEmpty(products)) {
			response.setResponseMessage("No products found");
			response.setSuccess(false);

			return new ResponseEntity<ProductResponseDto>(response, HttpStatus.OK);
		}

		response.setProducts(products);
		response.setResponseMessage("Product fetched success");
		response.setSuccess(true);

		return new ResponseEntity<ProductResponseDto>(response, HttpStatus.OK);
	}

	@GetMapping("search/seller-wise")
	public ResponseEntity<ProductResponseDto> searchSellerProductsByName(
			@RequestParam(name = "productName") String productName, @RequestParam(name = "sellerId") int sellerId) {
		LOG.info("request received for searching the seller products");

		ProductResponseDto response = new ProductResponseDto();

		if (productName == null || sellerId == 0) {
			response.setResponseMessage("missing input");
			response.setSuccess(false);

			return new ResponseEntity<ProductResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		User seller = this.userService.getUserById(sellerId);

		if (seller == null) {
			response.setResponseMessage("seller not found");
			response.setSuccess(false);

			return new ResponseEntity<ProductResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		List<Product> products = this.productService.searchProductNameAndSellerAndStatusIn(productName, seller,
				Arrays.asList(Constants.ProductStatus.ACTIVE.value()));

		if (CollectionUtils.isEmpty(products)) {
			response.setResponseMessage("No products found");
			response.setSuccess(false);

			return new ResponseEntity<ProductResponseDto>(response, HttpStatus.OK);
		}

		response.setProducts(products);
		response.setResponseMessage("Product fetched success");
		response.setSuccess(true);

		return new ResponseEntity<ProductResponseDto>(response, HttpStatus.OK);
	}

	@GetMapping("fetch/category-wise")
	public ResponseEntity<ProductResponseDto> fetchAllProductsByCategory(
			@RequestParam(name = "categoryId") int categoryId) {

		LOG.info("request received for fetching all the products by category");

		ProductResponseDto response = new ProductResponseDto();

		if (categoryId == 0) {
			response.setResponseMessage("category id missing");
			response.setSuccess(false);

			return new ResponseEntity<ProductResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		Category category = this.categoryService.getCategoryById(categoryId);

		if (category == null) {
			response.setResponseMessage("category not found");
			response.setSuccess(false);

			return new ResponseEntity<ProductResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		List<Product> products = this.productService.getAllProductByCategoryAndStatusIn(category,
				Arrays.asList(Constants.ProductStatus.ACTIVE.value()));

		if (CollectionUtils.isEmpty(products)) {
			response.setResponseMessage("No products found");
			response.setSuccess(false);

			return new ResponseEntity<ProductResponseDto>(response, HttpStatus.OK);
		}

		response.setProducts(products);
		response.setResponseMessage("Product fetched success");
		response.setSuccess(true);

		return new ResponseEntity<ProductResponseDto>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/{productImageName}", produces = "image/*")
	public void fetchProductImage(@PathVariable("productImageName") String productImageName, HttpServletResponse resp) {
		Resource resource = storageService.load(productImageName);
		if (resource != null) {
			try (InputStream in = resource.getInputStream()) {
				ServletOutputStream out = resp.getOutputStream();
				FileCopyUtils.copy(in, out);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}

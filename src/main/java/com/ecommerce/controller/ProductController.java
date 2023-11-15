package com.ecommerce.controller;

import com.ecommerce.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/product")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

	@Autowired

	private ProductResource productResource;


	@PostMapping("add")
	@Operation(summary = "Api to add product")
	public ResponseEntity<CommonApiResponse> addProduct(ProductAddRequest productDto) {
		return this.productResource.addProduct(productDto);
	}

	@PutMapping("update/detail")
	@Operation(summary = "Api to update product details")
	public ResponseEntity<CommonApiResponse> updateProductDetails(@RequestBody ProductDetailUpdateRequest request) {
		System.out.println(request);
		return this.productResource.updateProductDetail(request);
	}

	@PutMapping("update/image")
	@Operation(summary = "Api to update product images")
	public ResponseEntity<CommonApiResponse> updateProductDetails(ProductAddRequest request) {
		return this.productResource.updateProductImage(request);
	}

	@DeleteMapping("delete")
	@Operation(summary = "Api to delete product")
	public ResponseEntity<CommonApiResponse> deleteProduct(@RequestParam("productId") int productId,
			@RequestParam("sellerId") int sellerId) {
		return this.productResource.deleteProduct(productId, sellerId);
	}

	@GetMapping("fetch")
	@Operation(summary = "Api to fetch product by Id")
	public ResponseEntity<ProductResponseDto> fetchProductById(@RequestParam(name = "productId") int productId) {
		return this.productResource.fetchProductById(productId);
	}

	@GetMapping("fetch/all")
	@Operation(summary = "Api to fetch all active product")
	public ResponseEntity<ProductResponseDto> fetchAllProduct(

	) {
		return this.productResource.fetchAllProducts();
	}

	@GetMapping("fetch/seller-wise")
	@Operation(summary = "Api to fetch all seller active product")
	public ResponseEntity<ProductResponseDto> fetchAllSellerProduct(@RequestParam(name = "sellerId") int sellerId) {
		return this.productResource.fetchAllSellerProducts(sellerId);
	}

	@GetMapping("fetch/seller-wise/category-wise")
	@Operation(summary = "Api to fetch all seller active product")
	public ResponseEntity<ProductResponseDto> fetchAllSellerProductAndCategory(
			@RequestParam(name = "sellerId") int sellerId, @RequestParam(name = "categoryId") int categoryId) {
		return this.productResource.fetchAllSellerProductsWithCategory(sellerId, categoryId);
	}

	@GetMapping("search")
	@Operation(summary = "Api to search the products by name")
	public ResponseEntity<ProductResponseDto> searchProductsByName(
			@RequestParam(name = "productName") String productName) {
		return this.productResource.searchProductByName(productName);
	}

	@GetMapping("search/seller-wise")
	@Operation(summary = "Api to search the seller products by name")
	public ResponseEntity<ProductResponseDto> searchSellerProductsByName(
			@RequestParam(name = "productName") String productName, @RequestParam(name = "sellerId") int sellerId) {
		return this.productResource.searchSellerProductsByName(productName, sellerId);
	}

	@GetMapping("fetch/category-wise")
	@Operation(summary = "Api to fetch all products by category")
	public ResponseEntity<ProductResponseDto> fetchAllProductsByCategory(
			@RequestParam(name = "categoryId") int categoryId) {
		return this.productResource.fetchAllProductsByCategory(categoryId);
	}

	@GetMapping(value = "/{productImageName}", produces = "image/*")
	public void fetchProductImage(@PathVariable("productImageName") String productImageName, HttpServletResponse resp) {

		this.productResource.fetchProductImage(productImageName, resp);

	}

}

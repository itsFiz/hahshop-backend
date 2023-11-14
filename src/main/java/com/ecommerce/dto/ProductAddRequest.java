package com.ecommerce.dto;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.entity.Product;

import lombok.Data;

@Data
public class ProductAddRequest {
	
	private int id;
	
	private String name;

	private String description;

	private BigDecimal price;

	private int quantity;

	private int categoryId;

	private int sellerId;

	private MultipartFile image1;

	private MultipartFile image2;

	private MultipartFile image3;

	public static Product toEntity(ProductAddRequest dto) {
		Product entity = new Product();
		BeanUtils.copyProperties(dto, entity, "image1", "image2", "image3", "categoryId", "sellerId");
		return entity;
	}

}

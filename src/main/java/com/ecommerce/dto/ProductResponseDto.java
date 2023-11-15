package com.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.Model.Product;

import lombok.Data;

@Data
public class ProductResponseDto extends CommonApiResponse {
	
	private List<Product> products = new ArrayList<>();

}

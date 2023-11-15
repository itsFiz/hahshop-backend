package com.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.Model.Category;

import lombok.Data;

@Data
public class CategoryResponseDto extends CommonApiResponse {
	
	private List<Category> categories = new ArrayList<>(); 

}

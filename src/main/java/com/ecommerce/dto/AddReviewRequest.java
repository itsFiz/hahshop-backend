package com.ecommerce.dto;

import lombok.Data;

@Data
public class AddReviewRequest {

	private int userId;

	private int productId;

	private int star;

	private String review;

}

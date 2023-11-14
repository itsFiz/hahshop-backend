package com.ecommerce.dto;

import lombok.Data;

@Data
public class CartRequestDto {

	private int id;

	private int userId;

	private int productId;

	private int quantity;

}

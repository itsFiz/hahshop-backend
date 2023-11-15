package com.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.Model.Orders;

import lombok.Data;

@Data
public class OrderResponseDto extends CommonApiResponse {
	
	private List<Orders> orders = new ArrayList<>();

}

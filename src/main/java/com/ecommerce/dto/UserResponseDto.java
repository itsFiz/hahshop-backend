package com.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class UserResponseDto extends CommonApiResponse {
	
	private List<UserDto> users = new ArrayList<>();

}

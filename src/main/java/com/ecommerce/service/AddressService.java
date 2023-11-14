package com.ecommerce.service;

import java.util.List;

import com.ecommerce.entity.Address;
import com.ecommerce.entity.User;

public interface AddressService {
	
	Address addAddress(Address address);
	
	Address updateAddress(Address address);
	
	Address getAddressById(int addressId);

}

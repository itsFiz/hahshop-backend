package com.ecommerce.service;

import com.ecommerce.Model.Address;

public interface AddressService {
	
	Address addAddress(Address address);
	
	Address updateAddress(Address address);
	
	Address getAddressById(int addressId);

}

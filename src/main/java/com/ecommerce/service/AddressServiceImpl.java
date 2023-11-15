package com.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.Repositories.AddressRepo;
import com.ecommerce.Model.Address;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepo addressRepo;

	@Override
	public Address addAddress(Address address) {
		return addressRepo.save(address);
	}

	@Override
	public Address updateAddress(Address address) {
		return addressRepo.save(address);
	}

	@Override
	public Address getAddressById(int addressId) {
		Optional<Address> optionalAddress = addressRepo.findById(addressId);

		if (optionalAddress.isPresent()) {
			return optionalAddress.get();
		} else {
			return null;
		}

	}

}

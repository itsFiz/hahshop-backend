package com.ecommerce.Repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ecommerce.Model.Address;

@Repository
@Service
public interface AddressRepository extends JpaRepository<Address, Integer> {

    default Address addAddress(Address address) {
        return save(address);
    }

    default Address updateAddress(Address address) {
        return save(address);
    }

    default Address getAddressById(int addressId) {
        return findById(addressId).orElse(null);
    }
}

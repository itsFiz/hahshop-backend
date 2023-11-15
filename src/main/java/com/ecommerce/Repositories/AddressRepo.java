package com.ecommerce.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.Model.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer> {

}

package com.patika.kredinbizdeservice.repository;

import com.patika.kredinbizdeservice.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, Long> {
}

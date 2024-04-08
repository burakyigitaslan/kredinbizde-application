package com.patika.kredinbizdeservice.service.interfaces;

import com.patika.kredinbizdeservice.model.Address;

import java.util.List;
import java.util.Optional;

public interface IAddressService {
    Address createAddress(Address address);
    List<Address> getAllAddresses();
    Optional<Address> getAddressById(Long id);
    Address updateAddress(Address address);
    void deleteAddress(Long id);
}

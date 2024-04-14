package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.model.Address;
import com.patika.kredinbizdeservice.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAddress_Success() {
        Address address = new Address();
        address.setAddressTitle("Test Address");

        when(addressRepository.save(any(Address.class))).thenReturn(address);

        Address savedAddress = addressService.createAddress(address);

        assertNotNull(savedAddress);
        assertEquals("Test Address", savedAddress.getAddressTitle());

        verify(addressRepository, times(1)).save(address);
    }

    @Test
    void getAllAddresses_Success() {
        Address address1 = new Address();
        address1.setAddressTitle("Address 1");

        Address address2 = new Address();
        address2.setAddressTitle("Address 2");

        when(addressRepository.findAll()).thenReturn(Arrays.asList(address1, address2));

        List<Address> addresses = addressService.getAllAddresses();

        assertNotNull(addresses);
        assertEquals(2, addresses.size());

        verify(addressRepository, times(1)).findAll();
    }

    @Test
    void getAddressById_Exists_Success() {
        Address address = new Address();
        address.setId(1L);

        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));

        Optional<Address> optionalAddress = addressService.getAddressById(1L);

        assertTrue(optionalAddress.isPresent());
        assertEquals(1L, optionalAddress.get().getId());

        verify(addressRepository, times(1)).findById(1L);
    }

    @Test
    void getAddressById_NotExists_Success() {
        when(addressRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Address> optionalAddress = addressService.getAddressById(1L);

        assertFalse(optionalAddress.isPresent());

        verify(addressRepository, times(1)).findById(1L);
    }

    @Test
    void updateAddress_Success() {
        Address address = new Address();
        address.setId(1L);
        address.setAddressTitle("Test Address");

        when(addressRepository.save(any(Address.class))).thenReturn(address);

        Address updatedAddress = addressService.updateAddress(address);

        assertNotNull(updatedAddress);
        assertEquals(1L, updatedAddress.getId());
        assertEquals("Test Address", updatedAddress.getAddressTitle());

        verify(addressRepository, times(1)).save(address);
    }

    @Test
    void deleteAddress_Success() {
        doNothing().when(addressRepository).deleteById(1L);

        addressService.deleteAddress(1L);

        verify(addressRepository, times(1)).deleteById(1L);
    }
}
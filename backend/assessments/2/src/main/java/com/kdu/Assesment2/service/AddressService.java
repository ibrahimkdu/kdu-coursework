package com.kdu.Assesment2.service;

import com.kdu.Assesment2.model.Address;
import com.kdu.Assesment2.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {


    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository)
    {
        this.addressRepository=addressRepository;
    }
    public void saveAddress(Address address)
    {
        addressRepository.save(address);
    }


        public Address getUserById(Integer addressId) {
            return addressRepository.findById(addressId).orElse(null);
        }

        public List<Address> getAllAddress() {
            return addressRepository.findAll();
        }


}

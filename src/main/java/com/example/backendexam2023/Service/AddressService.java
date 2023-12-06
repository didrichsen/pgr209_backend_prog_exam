package com.example.backendexam2023.Service;

import com.example.backendexam2023.Model.Address.Address;
import com.example.backendexam2023.Repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address getAddressById(Long id){
        return addressRepository.findById(id).orElse(null);
    }
    
    public Address createAddress(Address address){
        List<Address> addresses = addressRepository.findAll();
        for(Address a : addresses){
            if (a.getStreetAddress().equals(address.getStreetAddress()) && a.getZipCode() == address.getZipCode()){
                throw new RuntimeException("Address already exists with id: " + a.getAddressId());
            }
        }
        return addressRepository.save(address);
    }

    public List<Address> getAllAddresses(){
        return addressRepository.findAll();
    }
    
    public void deleteAddress(Long id){
        addressRepository.deleteById(id);
    }
    public Address updateAddress(Address address){
        return addressRepository.save(address);
    }

}
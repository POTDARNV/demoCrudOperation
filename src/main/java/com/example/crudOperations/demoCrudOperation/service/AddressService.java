package com.example.crudOperations.demoCrudOperation.service;

import com.example.crudOperations.demoCrudOperation.model.Address;
import com.example.crudOperations.demoCrudOperation.repo.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepo addressRepo;
    public Address addAddress(Address address)
    {
        return addressRepo.save(address);
    }
   // @Scheduled(fixedDelay = 5000)
    public List<Address> getAddresse()
    {
        List<Address> address =addressRepo.findAll();
        System.out.println(address);
        return address;

    }

    public void deleteAddress(int id)
    {
        addressRepo.deleteById(id);
    }
}

package com.example.crudOperations.demoCrudOperation.controller;

import com.example.crudOperations.demoCrudOperation.model.Address;
import com.example.crudOperations.demoCrudOperation.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
@Autowired
    private AddressService service;
@PostMapping
public Address address(@RequestBody Address address)
{
    return service.addAddress(address);
}
public List<Address> findAddress()
{
    return service.getAddresse();

}
public void deleteAddress(@RequestBody int id)
{
    service.deleteAddress(id);
    System.out.println("Address Deleted");
}
}

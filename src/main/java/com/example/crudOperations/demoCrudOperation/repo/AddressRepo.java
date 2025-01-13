package com.example.crudOperations.demoCrudOperation.repo;

import com.example.crudOperations.demoCrudOperation.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer> {
}

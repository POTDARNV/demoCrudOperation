package com.example.crudOperations.demoCrudOperation.repo;

import com.example.crudOperations.demoCrudOperation.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users,Integer> {
//OR role = :identifier
    Users findByEmail(String email);
    Users findByPhoneNumber(String phoneNumber);
    Users findByUsername(String username);
   /* @Query(value = "SELECT * FROM users WHERE username = :identifier OR email = :identifier OR phone_number = :identifier", nativeQuery = true)
    Users findByUsernameOrEmailOrPhoneNumber(String identifier);*/
   @Query(value = "SELECT * FROM users WHERE username = :identifier OR email = :identifier OR phone_number = :identifier ", nativeQuery = true)
    Users findByUsernameOrEmailOrPhoneNumber(String identifier);
//change

}

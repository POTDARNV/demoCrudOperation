package com.example.crudOperations.demoCrudOperation.service;

import com.example.crudOperations.demoCrudOperation.model.Users;
import com.example.crudOperations.demoCrudOperation.model.UsersPrincipal;
import com.example.crudOperations.demoCrudOperation.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService  implements UserDetailsService {
    @Autowired
    private UsersRepo usersRepo;
    @Override
    public UserDetails loadUserByUsername(String identify) throws UsernameNotFoundException {
        Users users = usersRepo.findByUsernameOrEmailOrPhoneNumber(identify);

        if (users==null){
            throw new UsernameNotFoundException("User not Found");
        }
       return new UsersPrincipal(users);
    }
}

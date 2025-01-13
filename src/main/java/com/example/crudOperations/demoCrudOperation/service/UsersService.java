package com.example.crudOperations.demoCrudOperation.service;

import com.example.crudOperations.demoCrudOperation.model.Users;
import com.example.crudOperations.demoCrudOperation.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsersService {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private JwtService jwtService;
private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

public Users addusers(Users users)
{
    if (users.getRole()==null||users.getRole().isEmpty())
    {
        users.setRole("ROLE_USER");
    } else if (users.getRole().equalsIgnoreCase("user")) {
        users.setRole("ROLE_USER");
    } else if (users.getRole().equalsIgnoreCase("admin")) {
        users.setRole("ROLE_ADMIN");
    }
    else {
        throw new RuntimeException("Role Is Not Matched");
    }
    users.setPassword(encoder.encode(users.getPassword()));
return usersRepo.save(users);
}

public List<Users> findAllUsers()
{
    return usersRepo.findAll();
}



//####VERIFY USER
public String verifyUser(Users users) {
    String identifier = users.getUsername();
    String Password = users.getPassword();
    Users existinguser = usersRepo.findByUsernameOrEmailOrPhoneNumber(identifier);

    Authentication authentication = manager
            .authenticate(new UsernamePasswordAuthenticationToken
                    (existinguser.getUsername(), Password));

    if (authentication.isAuthenticated())
    {
        String accessToken = jwtService.generateToken(existinguser.getUsername(), existinguser.getEmail(), existinguser.getPhoneNumber(),existinguser.getRole());
        String refreshToken = jwtService.generateRefreshToken(existinguser.getUsername(),existinguser.getEmail(), existinguser.getPhoneNumber(),existinguser.getRole());
        existinguser.setRefreshToken(refreshToken);
        usersRepo.save(existinguser);
        Map<String, Object> tokens = new HashMap<>();
        tokens.put("access_Token", accessToken);
        tokens.put("refresh_Token", refreshToken);
        tokens.put("token_Type", "Bearer");
        tokens.put("expires_in", 300000);

        return tokens.toString();
    }
    else {
        throw new RuntimeException("User not found.");
    }
}

public String generateRefreshToken(String  refreshToken)
{
    if (refreshToken == null || refreshToken.isEmpty()) {
        throw new IllegalArgumentException("Refresh token cannot be null or empty");
    }
    String username = jwtService.extractUsername(refreshToken);
    Users existUser=usersRepo.findByUsernameOrEmailOrPhoneNumber(username);
    if (existUser !=null&&refreshToken.equals(existUser.getRefreshToken()))
    {
        String newRefreshToken= jwtService.generateToken(
                existUser.getUsername(),
                existUser.getEmail(),existUser.getPhoneNumber(),existUser.getRole());
        existUser.setRefreshToken(newRefreshToken);
        usersRepo.save(existUser);
        return newRefreshToken;
    }
    else {
        return "User Not Found";
    }
}
}

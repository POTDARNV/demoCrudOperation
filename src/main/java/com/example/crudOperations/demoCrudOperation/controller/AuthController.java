package com.example.crudOperations.demoCrudOperation.controller;

import com.example.crudOperations.demoCrudOperation.model.Users;
import com.example.crudOperations.demoCrudOperation.repo.UsersRepo;
import com.example.crudOperations.demoCrudOperation.service.JwtService;
import com.example.crudOperations.demoCrudOperation.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private UsersService usersService;
    @Autowired
    private JwtService jwtService;
@PostMapping("/signin")
    public  Users Users (@RequestBody Users users){
return usersService.addusers(users);

    }

@PostMapping("/login")
public String login(@RequestBody Users users)
{
    System.out.println(users);
   return usersService.verifyUser(users);
}

    @PostMapping("/refresh_token")
    public ResponseEntity<?> generateNewToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");

        if (refreshToken == null || refreshToken.isEmpty()) {
            return ResponseEntity.badRequest().body("Refresh token is required");
        }
        try {
            String newRefreshToken = usersService.generateRefreshToken(refreshToken);
            return ResponseEntity.ok(Map.of("refreshToken", newRefreshToken));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
@GetMapping("/getAll")
    public List<Users> findAll()
    {
        return usersService.findAllUsers();
    }

    }

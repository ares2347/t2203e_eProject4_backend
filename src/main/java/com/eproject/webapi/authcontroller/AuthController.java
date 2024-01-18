package com.eproject.webapi.authcontroller;

import com.eproject.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authentication")
public class AuthController {
    
    @Autowired
    private UserService _userService;
    @PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) throws Exception{
        try {
            return new ResponseEntity<AuthResponse>(new AuthResponse(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<AuthResponse>(new AuthResponse(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        try {
            return new ResponseEntity<AuthResponse>(new AuthResponse(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<AuthResponse>(new AuthResponse(), HttpStatus.UNAUTHORIZED);
        }
    }

}

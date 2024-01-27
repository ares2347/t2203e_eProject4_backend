package com.eproject.webapi.authcontroller;

import com.eproject.data.usermodel.UserEntity;
import com.eproject.repository.user.IUserRepository;
import com.eproject.service.auth.JwtService;
import com.eproject.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private IUserRepository _userRepository;
    @Autowired
    private UserService _userService;

    @Autowired
    private JwtService _jwtService;

    @Autowired
    AuthenticationManager authenticationManager;
    @PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) throws Exception{
        try {
            authenticate(request.username, request.password);
            UserEntity user = _userService.getUserByUsername(request.username);
            String token = _jwtService.generateTokenLogin(user);
            return new ResponseEntity<AuthResponse>(new AuthResponse(token), HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<AuthResponse>(new AuthResponse(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        try {
            UserEntity user = _userService.addNewUser(request);
            String token = _jwtService.generateTokenLogin(user);
            return new ResponseEntity<AuthResponse>(new AuthResponse(token), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<AuthResponse>(new AuthResponse(), HttpStatus.UNAUTHORIZED);
        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}

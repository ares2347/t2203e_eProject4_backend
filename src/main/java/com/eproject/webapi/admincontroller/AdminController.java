package com.eproject.webapi.admincontroller;

import com.eproject.data.usermodel.UserEntity;
import com.eproject.repository.user.IUserRepository;
import com.eproject.service.auth.JwtService;
import com.eproject.service.user.UserService;
import com.eproject.webapi.authcontroller.AuthResponse;
import com.eproject.webapi.authcontroller.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService _userService;


    @PostMapping(path = "/create-brand", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserEntity> createBrand(@RequestBody CreateBrandAccountRequest request) {
        try {
            UserEntity user = _userService.addNewBrand(request);
            return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<UserEntity>(new UserEntity(), HttpStatus.UNAUTHORIZED);
        }
    }
}

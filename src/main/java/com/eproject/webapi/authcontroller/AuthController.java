package com.eproject.webapi.authcontroller;

import com.eproject.data.model.usermodel.RoleEntity;
import com.eproject.data.model.usermodel.UserEntity;
import com.eproject.data.model.usermodel.UserRolesEnum;
import com.eproject.service.auth.JwtService;
import com.eproject.service.user.UserService;
import com.eproject.webapi.BaseResponse;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService _userService;

    @Autowired
    private JwtService _jwtService;

    @Autowired
    AuthenticationManager authenticationManager;
    @PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity login(@RequestBody LoginRequest request) throws Exception{
        try {
            authenticate(request.username, request.password);
            UserEntity user = _userService.getUserByUsername(request.username);
            String token = _jwtService.generateTokenLogin(user);
            return new ResponseEntity<>(new AuthResponse(token), HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(new BaseResponse("Tài khoản hoặc mật khẩu không chính xác. Vui lòng kiểm tra lại."), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity register(@RequestBody @Valid RegisterRequest request, BindingResult bindingResult) {
        try {
            Set<RoleEntity> roles = new HashSet<>();
            roles.add(UserRolesEnum.USER);
            UserEntity user = _userService.addNewUser(request, roles);
            String token = _jwtService.generateTokenLogin(user);
            return new ResponseEntity<AuthResponse>(new AuthResponse(token), HttpStatus.OK);
        } catch (ConstraintViolationException ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(new BaseResponse("Email hoặc số điện thoại không hợp lệ. Vui lòng kiểm tra lại"), HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(new BaseResponse("Email hoặc số điện thoại đã được đăng ký. Vui lòng kiểm tra lại"), HttpStatus.BAD_REQUEST);
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

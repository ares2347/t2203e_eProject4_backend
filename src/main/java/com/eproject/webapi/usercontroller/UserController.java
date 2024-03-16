package com.eproject.webapi.usercontroller;

import com.eproject.data.dto.user.UserDto;
import com.eproject.data.model.usermodel.UserEntity;
import com.eproject.service.auth.JwtService;
import com.eproject.service.ticket.TicketService;
import com.eproject.service.user.IUserService;
import com.eproject.service.user.UserService;
import com.eproject.webapi.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    TicketService _ticketService;

    @Autowired
    private JwtService _jwtService;

    @Autowired
    private IUserService _userService;

    @GetMapping("/info")
    public ResponseEntity getUserInfo(HttpServletRequest request) {
        try {
            UserDto res = _userService.getCurrentUserInfo();
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity(new BaseResponse<>("Lỗi xảy ra khi lấy thông tin người dùng."), HttpStatus.BAD_REQUEST);
        }
    }
}

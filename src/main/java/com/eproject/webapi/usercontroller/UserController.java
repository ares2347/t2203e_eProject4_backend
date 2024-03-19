package com.eproject.webapi.usercontroller;

import com.eproject.data.dto.PageDto;
import com.eproject.data.dto.trip.TicketDto;
import com.eproject.data.dto.user.UserDto;
import com.eproject.data.model.usermodel.UserEntity;
import com.eproject.service.auth.JwtService;
import com.eproject.service.payment.IPaymentInterface;
import com.eproject.service.ticket.ITicketService;
import com.eproject.service.ticket.TicketService;
import com.eproject.service.user.IUserService;
import com.eproject.service.user.UserService;
import com.eproject.webapi.BaseResponse;
import com.eproject.webapi.paymentcontroller.ThirdPartyPaymentData;
import com.eproject.webapi.paymentcontroller.ThirdPartyPaymentResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    ITicketService _ticketService;
    @Autowired
    private IUserService _userService;
    @Autowired
    private IPaymentInterface _paymentInterface;

    @GetMapping("/info")
    public ResponseEntity getUserInfo(HttpServletRequest request) {
        try {
            UserDto res = _userService.getCurrentUserInfo();
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity(new BaseResponse<>("Lỗi xảy ra khi lấy thông tin người dùng."), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(name = "/ticket/my-tickets")
    public ResponseEntity getCurrentUserTickets(@RequestParam(name = "startDate") LocalDate startDate,
                                                @RequestParam(name = "pickupPoint") String pickupPoint,
                                                @RequestParam(name = "dropoffPoint") String dropoffPoint,
                                                @RequestParam(name = "page", defaultValue = "0") int page,
                                                @RequestParam(name = "size", defaultValue = "10") int size) {
        try {
            PageDto<TicketDto> res = _ticketService.getCurrentUserTickets(startDate, dropoffPoint, pickupPoint, page, size);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(new BaseResponse<>("Lỗi xảy ra khi lấy danh sách vé."), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(name = "/ticket/book-tickets", consumes = "application/json", produces = "application/json")
    public ResponseEntity bookTickets(BookTicketRequest request) {
        try {
            List<TicketDto> res = _ticketService.bookTickets(request);
            ThirdPartyPaymentResponse payment = _paymentInterface.createPayment(res);
            return new ResponseEntity<>(payment.data.checkoutUrl, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(new BaseResponse<>("Lỗi xảy ra khi đặt vé."), HttpStatus.BAD_REQUEST);
        }
    }
}

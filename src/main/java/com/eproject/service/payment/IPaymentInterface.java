package com.eproject.service.payment;

import com.eproject.data.dto.trip.TicketDto;
import com.eproject.webapi.paymentcontroller.ThirdPartyPaymentData;
import com.eproject.webapi.paymentcontroller.ThirdPartyPaymentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPaymentInterface {
    ThirdPartyPaymentResponse createPayment(List<TicketDto> items);
}

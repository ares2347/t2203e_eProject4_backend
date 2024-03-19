package com.eproject.webapi.paymentcontroller;

import com.eproject.data.dto.trip.TicketDto;
import com.eproject.webapi.usercontroller.BookTicketRequestDetail;

import java.math.BigDecimal;
import java.util.List;

public class ThirdPartyPaymentRequest {
    public String orderCode;
    public BigDecimal amount;
    public String description;
    public String buyerName;
    public String buyerEmail;
    public String buyerPhone;
    public List<TicketDto> items;
    public String signature;
    public String cancelUrl;
    public String returnUrl;

    public ThirdPartyPaymentRequest(String orderCode, BigDecimal amount, String description, String buyerName, String buyerEmail, String buyerPhone, String cancelUrl, String returnUrl, String signature) {
        this.orderCode = orderCode;
        this.amount = amount;
        this.description = description;
        this.buyerName = buyerName;
        this.buyerEmail = buyerEmail;
        this.buyerPhone = buyerPhone;
        this.cancelUrl = cancelUrl;
        this.returnUrl = returnUrl;
        this.signature = signature;
    }
}

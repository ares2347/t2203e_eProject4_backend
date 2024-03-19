package com.eproject.webapi.paymentcontroller;

import java.math.BigDecimal;

public class ThirdPartyPaymentData {
    public String bin;
    public String accountNumber;
    public String accountName;
    public BigDecimal amount;
    public String description;
    public String orderCode;
    public String curency;
    public String paymentLinkId;
    public String status;
    public String checkoutUrl;
    public String qrCode;
}

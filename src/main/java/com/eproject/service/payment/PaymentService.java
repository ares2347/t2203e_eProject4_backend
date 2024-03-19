package com.eproject.service.payment;

import com.eproject.data.dto.trip.TicketDto;
import com.eproject.data.model.brandmodel.BrandEntity;
import com.eproject.data.model.paymentmodel.PaymentEntity;
import com.eproject.data.model.paymentmodel.PaymentMethodEnum;
import com.eproject.data.model.paymentmodel.PaymentStatusEnum;
import com.eproject.data.model.usermodel.UserEntity;
import com.eproject.repository.PaymentRepository;
import com.eproject.service.auth.JwtService;
import com.eproject.webapi.paymentcontroller.ThirdPartyPaymentData;
import com.eproject.webapi.paymentcontroller.ThirdPartyPaymentRequest;
import com.eproject.webapi.paymentcontroller.ThirdPartyPaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaymentService implements IPaymentInterface {
    @Value("${config.payment.generateapi}")
    private String generateUrl;
    @Value("${config.payment.clientid}")
    private String clientId;
    @Value("${config.payment.apikey}")
    private String apiKey;
    @Value("${config.payment.checksumkey}")
    private String checksumKey;
    @Value("${config.payment.returnurl}")
    private String returnUrl;
    @Value("${config.payment.cancelurl}")
    private String cancelUrl;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private JwtService _jwtService;
    @Autowired
    private PaymentRepository _paymentRepository;

    @Override
    public ThirdPartyPaymentResponse createPayment(List<TicketDto> items) {
        UserEntity userEntity = _jwtService.getCurrentUser();
        BrandEntity brand = userEntity.getBrand();
        BigDecimal total = new BigDecimal(0);
        for (var item : items) {
            total = total.add(item.price);
        }
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setPaymentStatus(PaymentStatusEnum.PENDING);
        paymentEntity.setPaymentMethod(PaymentMethodEnum.BANKING);
        paymentEntity.setAmount(total);
        paymentEntity.setTitle(String.format("Thanh toán giao dịch", brand.getBrandName()));
        paymentEntity.setDescription(String.format("Thanh toán giao dịch với nhà xe %s chuyến %s %s vào %s ngày %",
                brand.getBrandName(),
                items.get(0).pickupPoint,
                items.get(0).dropoffPoint,
                items.get(0).startTime.toString(),
                items.get(0).startDate.toString()));
        PaymentEntity res = _paymentRepository.saveAndFlush(paymentEntity);
        //generate payment link
        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.add("x-client-id", clientId);
        headers.add("x-api-key", apiKey);
        String signature = "";
        ThirdPartyPaymentRequest thirdPartyPaymentRequest = new ThirdPartyPaymentRequest(
                res.getPaymentId().toString(),
                res.getAmount(),
                res.getDescription(),
                userEntity.getFullName(),
                userEntity.getEmail(),
                userEntity.getPhoneNumber(),
                cancelUrl,
                returnUrl,
                signature
                );
        HttpEntity<ThirdPartyPaymentRequest> entity = new HttpEntity<ThirdPartyPaymentRequest>(thirdPartyPaymentRequest, headers);
        ThirdPartyPaymentResponse response = restTemplate.exchange(generateUrl, HttpMethod.POST, entity, ThirdPartyPaymentResponse.class).getBody();
        return response;
    }
}

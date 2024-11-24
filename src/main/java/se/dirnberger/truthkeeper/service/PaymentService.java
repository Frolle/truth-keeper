package se.dirnberger.truthkeeper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.dirnberger.truthkeeper.config.PaymentConfig;
import se.dirnberger.truthkeeper.dto.PaymentRequest;
import se.dirnberger.truthkeeper.dto.PaymentResponse;

import java.io.IOException;
import java.nio.file.Files;

@Service
public class PaymentService {

    private final String caCert;
    private final String cert;
    private final String key;
    private final PaymentConfig config;


    @Autowired
    public PaymentService(PaymentConfig config) throws IOException {
        this.caCert = Files.readString(config.getCACert());
        this.cert = Files.readString(config.getCert());
        this.key = Files.readString(config.getKey());
        this.config = config;
    }

    public PaymentResponse sendPaymentRequest(String phoneNumber) {
        // Create an instance of RestTemplate
        String instructionUUID = "2F9C2F35D92340348F130D702E6C4CCC";
        RestTemplate restTemplate = new RestTemplate();
        PaymentRequest request = createTruthPayment(phoneNumber);
        // Define the Swish API endpoint
        String swishApiUrl = "https://mss.cpc.getswish.net/swish-cpcapi/api/v2/paymentrequests/" + instructionUUID;

        // Send a POST request to the Swish API and get the response
        ResponseEntity<PaymentResponse> response = restTemplate.postForEntity(swishApiUrl, request, PaymentResponse.class);

        // Return the response from the Swish API
        return response.getBody();
    }

    private PaymentRequest createTruthPayment(String phoneNumber) {
        PaymentRequest request = new PaymentRequest();

        request.setAmount(config.getAmount());
        request.setCurrency(config.getCurrency());
        request.setMessage(config.getMessage());
        request.setPayeeAlias(config.getPayeeAlias());
        request.setCallbackUrl(config.getCallBackUrl());
        request.setPayerAlias(phoneNumber);
        return request;
    }

    //TODO: Implement the callback processing from Swish
//    public boolean processPaymentCallback(PaymentCallback paymentCallback) {
//        // Extract the necessary information from the paymentCallback
//        String paymentId = paymentCallback.getPaymentId();
//        String status = paymentCallback.getStatus();
//
//        // Update your database based on the payment status
//        // For example, if you have a PaymentRepository that provides a method to update the status of a payment:
//        Payment payment = paymentRepository.findById(paymentId);
//        if (payment != null) {
//            payment.setStatus(status);
//            paymentRepository.save(payment);
//            return true;
//        } else {
//            return false;
//        }

}

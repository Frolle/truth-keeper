package se.dirnberger.truthkeeper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.dirnberger.truthkeeper.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @PostMapping("/request")
    public ResponseEntity<String> requestPayment(@RequestBody String phoneNumber) {
        var paymentResponse = paymentService.sendPaymentRequest(phoneNumber);

        // Return a response to the client based on the response from the Swish API
        if (paymentResponse.getStatus() == 201) {
            return new ResponseEntity<>("Payment request sent successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to send payment request", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //TODO: Implement the callback
//    @PostMapping("/callback")
//    public ResponseEntity<String> handleCallback(@RequestBody PaymentCallback paymentCallback) {
//        // This method will be called by the Swish API after the user approves the payment
//        // The PaymentCallback object should contain the information sent by the Swish API, such as the payment status and any user messages
//
//        // Process the payment callback as necessary
//        // This might involve updating your database to reflect the payment status
//
//        // For the purpose of this example, we'll assume this process is encapsulated in a method called processPaymentCallback
//        boolean isProcessed = processPaymentCallback(paymentCallback);
//
//        // Return a response based on whether the callback was processed successfully
//        if (isProcessed) {
//            return new ResponseEntity<>("Callback processed successfully", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Failed to process callback", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}

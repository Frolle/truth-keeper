package se.dirnberger.truthkeeper.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponse {
        private String id;
        private int status;
        private String userMessage;
}

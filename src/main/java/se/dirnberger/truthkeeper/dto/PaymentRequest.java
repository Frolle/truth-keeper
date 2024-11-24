package se.dirnberger.truthkeeper.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private String callbackUrl;
    private String payeeAlias;
    private String currency;
    private String payerAlias;
    private float amount;
    private String message;
}

package se.dirnberger.truthkeeper.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;

@Getter
@Configuration
public class PaymentConfig {

    private final Path key;
    private final Path cert;
    private final Path cACert;

    private String callBackUrl;
    private String payeeAlias;
    private float amount;
    private String currency;
    private String message;


    public PaymentConfig(
            @Value("${swish.key}") Path key,
            @Value("${swish.cert}") Path cert,
            @Value("${swish.cacert}") Path cACert,
            @Value("${swish.callBackUrl}") String callBackUrl,
            @Value("${swish.amount}") float amount,
            @Value("${swish.currency}") String currency,
            @Value("${swish.message}") String message,
            @Value("${swish.payeeAlias}") String payeeAlias
    ) {
        this.key = key;
        this.cert = cert;
        this.cACert = cACert;
        this.callBackUrl = callBackUrl;
        this.amount = amount;
        this.currency = currency;
        this.message = message;
        this.payeeAlias = payeeAlias;
    }
}

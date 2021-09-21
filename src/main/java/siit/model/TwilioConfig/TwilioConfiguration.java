package siit.model.TwilioConfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
@ConfigurationProperties("twilio")
public class TwilioConfiguration {
    private final  String accountSid= "ACb7ecd60e639e484c011b85dbd872e4c6" ;
    private  final String authToken = "f4854be29d0789e92da0415d829d0252";
    private   final String trialNumber = "+16122604228";





    public TwilioConfiguration() {

    }

    public String getAccountSid() {
        return accountSid;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getTrialNumber() {
        return trialNumber;
    }
}

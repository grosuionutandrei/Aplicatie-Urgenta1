package siit.sevices.Twilio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@org.springframework.stereotype.Service
public class TwilioService {
    private final SendSms sendSms;


    @Autowired
    public TwilioService(@Qualifier("twilio") TwilioSmsSender smsSender) {
        this.sendSms = smsSender;
    }

    public void sendSms(SmsRequest smsRequest) {
        sendSms.smsSend(smsRequest);
    }
}
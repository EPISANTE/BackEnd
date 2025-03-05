package Episante.back.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Value("${twilio.account_sid}")
    private String accountSid;

    @Value("${twilio.auth_token}")
    private String authToken;

    @Value("${twilio.phone_number}")
    private String twilioPhoneNumber;

    public void envoyerSms(String numeroDestinataire, String message) {
        Twilio.init(accountSid, authToken);

        Message sms = Message.creator(
                        new com.twilio.type.PhoneNumber(numeroDestinataire),
                        new com.twilio.type.PhoneNumber(twilioPhoneNumber),
                        message)
                .create();

        System.out.println(" SMS envoyé à " + numeroDestinataire + " : " + sms.getSid());
    }
}

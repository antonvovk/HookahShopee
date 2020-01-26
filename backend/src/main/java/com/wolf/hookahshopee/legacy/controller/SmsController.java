package com.wolf.hookahshopee.legacy.controller;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/sms")
public class SmsController {

    public static final String ACCOUNT_SID = "AC1d49783ced3b793a4d21fb93ec056bca";
    public static final String AUTH_TOKEN = "06d3329162ccc214b13e800ad302f8de";
    public static final String TWILIO_NUMBER = "+15403182110";

    @GetMapping
    public String send() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        return Message.creator(
                new PhoneNumber("+380509827026"),
                new PhoneNumber(TWILIO_NUMBER),
                "Sample Twilio SMS using Java")
                .create().toString();
    }
}

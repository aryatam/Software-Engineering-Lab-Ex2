// SmsMessageService.java
package edu.sharif.selab.services;

import edu.sharif.selab.models.SmsMessage;

public class SmsMessageService implements SmsService {
    @Override
    public void sendSms(SmsMessage sms) {
        if (!validatePhoneNumber(sms.getSourcePhoneNumber())
                || !validatePhoneNumber(sms.getTargetPhoneNumber())) {
            System.out.println("⚠️ Invalid phone number.");
            return;
        }
        System.out.println("📩 SMS from "
                + sms.getSourcePhoneNumber()
                + " to "
                + sms.getTargetPhoneNumber()
                + ": "
                + sms.getContent());
    }

    private boolean validatePhoneNumber(String num) {
        if (num == null || num.length() != 11) return false;
        for (char c : num.toCharArray())
            if (!Character.isDigit(c)) return false;
        return true;
    }
}
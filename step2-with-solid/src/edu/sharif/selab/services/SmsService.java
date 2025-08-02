package edu.sharif.selab.services;

import edu.sharif.selab.models.SmsMessage;

public interface SmsService {
    void sendSms(SmsMessage sms);
}

package edu.sharif.selab.services;

import edu.sharif.selab.models.TelegramMessage;

/**
 * A MessageService implementation for Telegram.
 * Throws on SMS/Email calls, and provides its own sendTelegramMessage(...) method.
 */
public class TelegramMessageService implements MessageService {

    @Override
    public void sendSmsMessage(edu.sharif.selab.models.SmsMessage smsMessage) {
        throw new UnsupportedOperationException("SMS not supported by TelegramMessageService");
    }

    @Override
    public void sendEmailMessage(edu.sharif.selab.models.EmailMessage emailMessage) {
        throw new UnsupportedOperationException("Email not supported by TelegramMessageService");
    }

    /**
     * First validates source and target IDs, then “sends” the Telegram message.
     */
    public void sendTelegramMessage(TelegramMessage telegramMessage) {
        String src = telegramMessage.getSourceId();
        String dst = telegramMessage.getTargetId();

        if (!validateId(src) || !validateId(dst)) {
            System.out.println("⚠️  Invalid Telegram ID(s). Cannot send message.");
            return;
        }

        // simulate sending
        System.out.println("📲 Sending Telegram message from ["
                + src + "] to [" + dst + "]: "
                + telegramMessage.getContent());
    }

    /**
     * Simple non-empty check. You can extend this to match “[0-9]+” or other ID‐formats.
     */
    private boolean validateId(String id) {
        return id != null && !id.trim().isEmpty();
    }
}

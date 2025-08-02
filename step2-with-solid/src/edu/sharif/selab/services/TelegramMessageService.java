package edu.sharif.selab.services;
import edu.sharif.selab.models.TelegramMessage;

public class TelegramMessageService implements TelegramService {
    @Override
    public void sendTelegram(TelegramMessage tg) {
        if (!validateId(tg.getSourceId()) || !validateId(tg.getTargetId())) {
            System.out.println("⚠️ Invalid Telegram ID(s).");
            return;
        }
        System.out.println("📲 Telegram from "
                + tg.getSourceId()
                + " to "
                + tg.getTargetId()
                + ": "
                + tg.getContent());
    }

    private boolean validateId(String id) {
        return id != null && !id.trim().isEmpty();
    }
}

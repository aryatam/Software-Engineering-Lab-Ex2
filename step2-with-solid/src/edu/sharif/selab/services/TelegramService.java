// TelegramService.java
package edu.sharif.selab.services;

import edu.sharif.selab.models.TelegramMessage;

public interface TelegramService {
    void sendTelegram(TelegramMessage telegram);
}

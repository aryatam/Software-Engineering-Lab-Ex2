// Main.java
package edu.sharif.selab;

import edu.sharif.selab.models.*;
import edu.sharif.selab.services.*;

import java.util.Scanner;

public class Main {

    private final SmsService smsService;
    private final EmailService emailService;
    private final TelegramService telegramService;
    private final Scanner scanner = new Scanner(System.in);

    public Main(SmsService smsService,
                EmailService emailService,
                TelegramService telegramService) {
        this.smsService = smsService;
        this.emailService = emailService;
        this.telegramService = telegramService;
    }

    public void run() {
        while (true) {
            System.out.println("Choose sending method:\n1) SMS\n2) Email\n3) Telegram\n(other to exit)");
            String choice = scanner.nextLine();
            Message msg = null;
            switch (choice) {
                case "1" -> {
                    System.out.print("Source phone: ");
                    String src = scanner.nextLine();
                    System.out.print("Target phone: ");
                    String dst = scanner.nextLine();
                    System.out.print("Text: ");
                    String txt = scanner.nextLine();
                    msg = new SmsMessage(src, dst, txt);
                    smsService.sendSms((SmsMessage) msg);
                }
                case "2" -> {
                    System.out.print("Source email: ");
                    String src = scanner.nextLine();
                    System.out.print("Target email: ");
                    String dst = scanner.nextLine();
                    System.out.print("Text: ");
                    String txt = scanner.nextLine();
                    msg = new EmailMessage(src, dst, txt);
                    emailService.sendEmail((EmailMessage) msg);
                }
                case "3" -> {
                    System.out.print("Source ID: ");
                    String src = scanner.nextLine();
                    System.out.print("Target ID: ");
                    String dst = scanner.nextLine();
                    System.out.print("Text: ");
                    String txt = scanner.nextLine();
                    msg = new TelegramMessage(src, dst, txt);
                    telegramService.sendTelegram((TelegramMessage) msg);
                }
                default -> {
                    System.out.println("Goodbye!");
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        SmsService sms = new SmsMessageService();
        EmailService email = new EmailMessageService();
        TelegramService tg = new TelegramMessageService();

        new Main(sms, email, tg).run();
    }
}

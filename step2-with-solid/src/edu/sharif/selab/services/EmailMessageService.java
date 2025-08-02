package edu.sharif.selab.services;

import edu.sharif.selab.models.EmailMessage;
import java.util.regex.Pattern;

public class EmailMessageService implements EmailService {
    @Override
    public void sendEmail(EmailMessage email) {
        if (!validateEmail(email.getSourceEmailAddress())
                || !validateEmail(email.getTargetEmailAddress())) {
            System.out.println("⚠️ Invalid email address.");
            return;
        }
        System.out.println("📧 Email from "
                + email.getSourceEmailAddress()
                + " to "
                + email.getTargetEmailAddress()
                + ": "
                + email.getContent());
    }

    private boolean validateEmail(String e) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return e != null && Pattern.compile(regex).matcher(e).matches();
    }
}

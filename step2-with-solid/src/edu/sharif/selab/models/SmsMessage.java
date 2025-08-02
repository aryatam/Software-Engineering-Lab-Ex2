package edu.sharif.selab.models;

public class SmsMessage extends Message {
    private final String sourcePhoneNumber;
    private final String targetPhoneNumber;

    public SmsMessage(String sourcePhoneNumber, String targetPhoneNumber, String content) {
        super(content);
        this.sourcePhoneNumber = sourcePhoneNumber;
        this.targetPhoneNumber = targetPhoneNumber;
    }
    public String getSourcePhoneNumber() {
        return sourcePhoneNumber;
    }
    public String getTargetPhoneNumber() {
        return targetPhoneNumber;
    }
}

package edu.sharif.selab.models;

public class EmailMessage extends Message {
    private final String sourceEmailAddress;
    private final String targetEmailAddress;

    public EmailMessage(String sourceEmailAddress, String targetEmailAddress, String content) {
        super(content);
        this.sourceEmailAddress = sourceEmailAddress;
        this.targetEmailAddress = targetEmailAddress;
    }
    public String getSourceEmailAddress() {
        return sourceEmailAddress;
    }
    public String getTargetEmailAddress() {
        return targetEmailAddress;
    }
}

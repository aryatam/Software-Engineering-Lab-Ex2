package edu.sharif.selab.models;

public class TelegramMessage extends Message {
    private final String sourceId;
    private final String targetId;

    public TelegramMessage(String sourceId, String targetId, String content) {
        super(content);
        this.sourceId = sourceId;
        this.targetId = targetId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public String getTargetId() {
        return targetId;
    }
}

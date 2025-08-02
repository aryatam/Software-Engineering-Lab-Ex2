package edu.sharif.selab.models;

public class TelegramMessage extends Message {
    private final String sourceId;
    private final String targetId;
    private final String content;

    public TelegramMessage(String sourceId, String targetId, String content) {
        this.sourceId = sourceId;
        this.targetId = targetId;
        this.content = content;
    }

    public String getSourceId() {
        return sourceId;
    }

    public String getTargetId() {
        return targetId;
    }

    public String getContent() {
        return content;
    }
}

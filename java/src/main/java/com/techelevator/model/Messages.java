package com.techelevator.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class Messages {
    @NotEmpty
    private int message_id;
    @NotNull
    @NotEmpty
    private String sender;
    @NotNull
    @NotEmpty
    private String receiver;
    @NotNull
    @NotEmpty
    private LocalDate timestamp;
    @NotNull
    @NotEmpty
    private String messageContent;
    @NotNull
    private boolean read;

    public Messages() {
    }

    public Messages(int message_id, String sender, String receiver, LocalDate timestamp, String messageContent, boolean read) {
        this.message_id = message_id;
        this.sender = sender;
        this.receiver = receiver;
        this.timestamp = timestamp;
        this.messageContent = messageContent;
        this.read = read;
    }

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    @Override
    public String toString() {
        return "Messages{" +
                "message_id=" + message_id +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", timestamp=" + timestamp +
                ", messageContent='" + messageContent + '\'' +
                ", read=" + read +
                '}';
    }
}

package com.techelevator.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class Message {

    @NotEmpty
    private int messageID;

    @NotNull
    @NotEmpty
    private String messageText;

    @NotNull
    @NotEmpty
    private String messageSender;

    @NotNull
    @NotEmpty
    private String messageReceiver;

    @NotNull
    @NotEmpty
    private LocalDateTime messageTimestamp;

    private boolean read;

    public Message() {
    }

    public Message(int messageID, String messageText, int messageSenderID,
                   int messageReceiver, LocalDateTime messageTimestamp)
    {
        this.messageID = messageID;
        this.messageText = messageText;
        this.messageSender = messageSenderID;
        this.messageReceiverID = messageReceiverID;
        this.messageTimestamp = messageTimestamp;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public int getMessageSenderID() {
        return messageSenderID;
    }

    public void setMessageSenderID(int messageSenderID) {
        this.messageSenderID = messageSenderID;
    }

    public int getMessageReceiverID() {
        return messageReceiverID;
    }

    public void setMessageReceiverID(int messageReceiverID) {
        this.messageReceiverID = messageReceiverID;
    }

    public LocalDateTime getMessageTimestamp() {
        return messageTimestamp;
    }

    public void setMessageTimestamp(LocalDateTime messageTimestamp) {
        this.messageTimestamp = messageTimestamp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageID=" + messageID +
                ", messageText='" + messageText + '\'' +
                ", messageSenderID=" + messageSenderID +
                ", messageReceiverID=" + messageReceiverID +
                ", messageTimestamp=" + messageTimestamp +
                '}';
    }
}

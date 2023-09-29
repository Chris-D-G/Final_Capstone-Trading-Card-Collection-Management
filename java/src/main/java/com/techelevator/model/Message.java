package com.techelevator.model;

import java.time.LocalDateTime;

public class Message {

    private int messageID;
    private String messageText;
    private int messageSenderID;
    private int messageReceiverID;
    private LocalDateTime messageTimestamp;

    public Message() {
    }

    public Message(int messageID, String messageText, int messageSenderID,
                   int messageReceiverID, LocalDateTime messageTimestamp)
    {
        this.messageID = messageID;
        this.messageText = messageText;
        this.messageSenderID = messageSenderID;
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

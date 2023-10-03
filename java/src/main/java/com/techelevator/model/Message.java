package com.techelevator.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class Message {
<<<<<<< HEAD

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

=======
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
>>>>>>> 35b9495f6be25e13d5da969e708aa9884836bdf1
    @NotNull
    @NotEmpty
    private LocalDateTime messageTimestamp;
    @NotNull
    private boolean read;

    private boolean read;

    public Message() {
    }

<<<<<<< HEAD
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
=======
>>>>>>> 35b9495f6be25e13d5da969e708aa9884836bdf1

}

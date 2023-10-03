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
    @NotNull
    private boolean read;

    public Message() {
    }


}

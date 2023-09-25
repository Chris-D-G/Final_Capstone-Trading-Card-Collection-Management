package com.techelevator.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class Collection {
    private int entryId;
    @NotBlank
    private String cardId;
    @Positive
    private  int userId;
    @Positive
    private int tcgId;
    @Positive
    private int qty;

    public Collection() {
    }

    /*
    Full constructor used for testing purposes.
    */

    public Collection(String cardId, int userId, int tcgId, int qty) {
        this.cardId = cardId;
        this.userId = userId;
        this.tcgId = tcgId;
        this.qty = qty;
    }

    public int getEntryId() {
        return entryId;
    }

    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTcgId() {
        return tcgId;
    }

    public void setTcgId(int tcgId) {
        this.tcgId = tcgId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}

package com.techelevator.model;

import javax.validation.constraints.NotBlank;
import java.util.ResourceBundle;

public class Card {
    //id is scryfall-specific id
    @NotBlank
    private String id;
    @NotBlank
    private int tcgId;
    @NotBlank
    private String name;

    //image url is of full card
    @NotBlank
    private String imageUrl;

    private String smallImgUrl;

    //url takes you to full page card notation on scryfall
    @NotBlank
    private String scryfallUrl;

    public Card() {}
/*
Full constructor used for testing purposes.
 */
    public Card(String id, int tcgId, String name, String imageUrl,String smallImgUrl, String scryfallUrl) {
        this.id = id;
        this.tcgId = tcgId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.scryfallUrl = scryfallUrl;
        this.smallImgUrl = smallImgUrl;
    }

    public String getSmallImgUrl() {
        return smallImgUrl;
    }

    public void setSmallImgUrl(String smallImgUrl) {
        this.smallImgUrl = smallImgUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTcgId() {
        return tcgId;
    }

    public void setTcgId(int tcgId) {
        this.tcgId = tcgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getScryfallUrl() {
        return scryfallUrl;
    }

    public void setScryfallUrl(String scryfallUrl) {
        this.scryfallUrl = scryfallUrl;
    }
}

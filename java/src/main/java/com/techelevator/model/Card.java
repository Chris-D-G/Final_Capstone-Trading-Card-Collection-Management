package com.techelevator.model;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class Card {
    //id is scryfall-specific id
    @NotBlank
    private String id;
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
    private String reverseImgUrl;
    private String smallReverseImgUrl;
    private List<String> colors = new ArrayList<>();
    @NotBlank
    private List<String> colorIdentity = new ArrayList<>();
    @NotBlank
    private int setId;
    @NotBlank
    private String setCode;
    @NotBlank
    private String setName;
    @NotBlank
    private String collectorNumber;
    @NotBlank
    private List<String> legalities;
    private String layout;
    private double cmc;
    private int edhrecRank;
    public Card() {}
/*
Full constructor used for testing purposes.
 */
    public Card(String id, int tcgId, String name, String imageUrl,
                String smallImgUrl, String scryfallUrl, List<String> colorIdentity,
                int setId, String setCode, String setName, String collectorNumber, List<String> legalities,
                List<String> colors, String reverseImgUrl, String smallReverseImgUrl, String layout, double cmc,
                int edhrecRank) {
        this.id = id;
        this.tcgId = tcgId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.scryfallUrl = scryfallUrl;
        this.smallImgUrl = smallImgUrl;
        this.reverseImgUrl = reverseImgUrl;
        this.smallReverseImgUrl = smallReverseImgUrl;
        this.colors = colors;
        this.colorIdentity = colorIdentity;
        this.setId = setId;
        this.setCode = setCode;
        this.setName = setName;
        this.collectorNumber = collectorNumber;
        this.legalities = legalities;
        this.layout = layout;
        this.cmc = cmc;
        this.edhrecRank = edhrecRank;
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

    public String getReverseImgUrl() {
        return reverseImgUrl;
    }

    public void setReverseImgUrl(String reverseImgUrl) {
        this.reverseImgUrl = reverseImgUrl;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public List<String> getColorIdentity() {
        return colorIdentity;
    }

    public void setColorIdentity(List<String> colorIdentity) {
        this.colorIdentity = colorIdentity;
    }

    public int getSetId() {
        return setId;
    }

    public void setSetId(int setId) {
        this.setId = setId;
    }

    public String getSetCode() {
        return setCode;
    }

    public void setSetCode(String setCode) {
        this.setCode = setCode;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getCollectorNumber() {
        return collectorNumber;
    }

    public void setCollectorNumber(String collectorNumber) {
        this.collectorNumber = collectorNumber;
    }

    public List<String> getLegalities() {
        return legalities;
    }

    public void setLegalities(List<String> legalities) {
        this.legalities = legalities;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public double getCmc() {
        return cmc;
    }

    public void setCmc(double cmc) {
        this.cmc = cmc;
    }

    public int getEdhrecRank() {
        return edhrecRank;
    }

    public void setEdhrecRank(int edhrecRank) {
        this.edhrecRank = edhrecRank;
    }

    public String getSmallReverseImgUrl() {
        return smallReverseImgUrl;
    }

    public void setSmallReverseImgUrl(String smallReverseImgUrl) {
        this.smallReverseImgUrl = smallReverseImgUrl;
    }
}

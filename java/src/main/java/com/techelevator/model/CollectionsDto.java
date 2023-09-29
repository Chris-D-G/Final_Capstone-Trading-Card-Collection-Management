package com.techelevator.model;

public class CollectionsDto {
    private int collectionId;
    private String username;
    private int tcgId;
    private String collectionName;

    public CollectionsDto() {
    }

    public CollectionsDto(int collectionId, String username, int tcgId, String collectionName) {
        this.collectionId = collectionId;
        this.username = username;
        this.tcgId = tcgId;
        this.collectionName = collectionName;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTcgId() {
        return tcgId;
    }

    public void setTcgId(int tcgId) {
        this.tcgId = tcgId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }
}

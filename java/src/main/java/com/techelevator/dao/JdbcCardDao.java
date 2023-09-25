package com.techelevator.dao;

import com.techelevator.model.Card;

import java.util.List;

//Define this class as an implementation of CardDao
public class JdbcCardDao implements CardDao {

    @Override
    public Card getCardById(String cardID) {
        return null;
    }

    @Override
    public Card getCardByTitle(String cardTitle) {
        return null;
    }

    @Override
    public List<Card> getQueriedCardsByTitle(String cardTitle) {
        return null;
    }
}

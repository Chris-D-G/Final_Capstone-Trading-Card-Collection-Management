package com.techelevator.dao;

import com.techelevator.model.Card;

import java.util.List;

//This interface will define the methods needed to be identified as a Card data access object
public interface CardDao {


    /**
     * Method to query a card object using a card's ID
     * as a search parameter
     *
     * @param cardID - card ID to search by
     * @return Card object that exactly matches the search parameter
     */
    Card getCardById(String cardID);

    /**
     * Method to query a card object using a card's
     * title as a search parameter
     *
     * @param cardTitle card title to search by
     * @return Card object that exactly matches the search parameter
     */
    Card getCardByTitle(String cardTitle);

    /**
     * Method to query a list of cards using a card
     * title as a search parameter
     * @param cardTitle card title to search by
     * @return List of card objects that may match the search parameter
     */
    List<Card> getQueriedCardsByTitle(String cardTitle);

}

package com.techelevator.dao;

import com.techelevator.model.Card;
import com.techelevator.model.Collection;
import org.springframework.stereotype.Component;

import java.util.List;

public interface CollectionsDao {
    /*
    returns list of Collections{id,name,tcg}
     */
    List<Collection> getAllCollections();

    /*
    returns list of Collections{id,name,tcg} further filtered by TCG
     */
    List<Collection> getCollectionsByTCG(int tcgId);

    /*
    returns filtered list of Collections{id,name,tcg} by user,
    will be derived by using the principal => username => user-id
    to preform the actual query
     */
    List<Collection> getAllUserCollections(String username);

    /*
    returns filtered list of Collections{id,name,tcg} by user,
    will be derived by using the principal => username => user-id
    to preform the actual query-- further filtered by tcgId
     */
    List<Collection> getUserCollectionsByTCG(String username, int tcgId);


    Card getCollectionEntryByIds(int entryId, String cardId);
    List<Card> getCollectionEntryByQty(int qty, int collectionId);

    /*
    returns a list of card objects by collectionId
     */
    List<Card> getCardsByCollectionId(int collectionId);

    Collection getCollectionById(int collectionId);


    int addCollection(Collection collection, String username);
    int removeCollection(int collectionId);

    int addCardToCollection(Card card, int collectionId);

}

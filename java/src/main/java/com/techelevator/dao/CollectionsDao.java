package com.techelevator.dao;

import com.techelevator.model.Card;
import com.techelevator.model.Collection;

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


    Collection getCollectionEntryByIds(int entryId, String cardId);
    List<Collection> getCollectionEntryByQty(int qty);

    /*
    returns a list of card objects by collectionId
     */
    List<Card> getCardsByCollectionId(int collectionId);


    int addCollection(Collection collection);
    int removeCollectionEntry(int collectionId);
    int removeWholeCollection(String username, int collectionId);

}

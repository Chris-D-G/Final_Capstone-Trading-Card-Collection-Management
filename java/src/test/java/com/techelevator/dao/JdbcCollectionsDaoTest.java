package com.techelevator.dao;

import com.techelevator.model.CardDto;
import com.techelevator.model.Collection;
import com.techelevator.model.CollectionsDto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class JdbcCollectionsDaoTest extends BaseDaoTests{

    private final Collection TEST_COLLECTION1 = new Collection(400,"test1",1);
    private final Collection TEST_COLLECTION2 = new Collection(401,"test1",1);
    CollectionsDao cdao;
    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        cdao = new JdbcCollectionsDao(jdbcTemplate);
    }

    @Test
    public void getAllCollections() {
        List<CollectionsDto> actual = cdao.getAllCollections();
        assertEquals(3,actual.size());
    }

    @Test
    public void getAllUserCollections() {
        List<Collection> actual = cdao.getAllUserCollections("user1");
        assertEquals(1,actual.size());
        List<Collection> actual2 = cdao.getAllUserCollections("user2");
        assertEquals(1,actual.size());
    }

    @Test
    public void getCardsByCollectionId() {
        List<CardDto> actual = cdao.getCardsByCollectionId(100);
        assertEquals(5,actual.size());
    }

    @Test
    public void addCollection() {
       int expectedId =  cdao.addCollection(TEST_COLLECTION1,"username");
        Collection expected =cdao.getCollectionById(expectedId);
       assertCollectionsMatch(expected,TEST_COLLECTION1);
    }

    @Test
    public void removeCollection() {
        List<Collection> actual1 = cdao.getAllUserCollections("username");
        cdao.removeCollection(11111111);
        List<Collection> actual2 = cdao.getAllUserCollections("username");
        assertNotEquals(actual1.size(),actual2.size());
    }

    @Test
    public void addCardToCollection() {
    }

    @Test
    public void deleteCardFromCollection() {
    }

    @Test
    public void getCollectionById() {
    }

    @Test
    public void getUserForCollectionId() {
    }

    @Test
    public void getCountOfCardsInCollection() {
    }

    @Test
    public void updateCollection() {
    }

    private void assertCollectionsMatch(Collection expected, Collection actual){
        assertEquals( expected.getId(), actual.getId());
        assertEquals( expected.getTcgId(), actual.getTcgId());
        assertEquals( expected.getName(), actual.getName());
    }
}
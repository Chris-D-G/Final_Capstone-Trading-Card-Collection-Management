package com.techelevator.dao;

import com.techelevator.model.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcCollectionsDao implements CollectionsDao{


    private UserDao userDao;
    private JdbcTemplate jdbcTemplate;
    private CardDao cardDao;

    public JdbcCollectionsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        userDao = new JdbcUserDao(jdbcTemplate);
        cardDao = new JdbcCardDao(jdbcTemplate);
    }

    /**
     *
     * @return A list of all collections in the database
     */

    @Override
    public List<CollectionsDto> getAllCollections() {
        //new list
        List<CollectionsDto> collectionList = new ArrayList<>();
        Collection collection = new Collection();
        CollectionsDto dto = new CollectionsDto();
        //grab all collections from database
        String sql = "select * from collections;";
        String userSql = "select username from users join collections_user on users.user_id = collections_user.user_id where collection_id = ?;";
        try{
            SqlRowSet rowset = jdbcTemplate.queryForRowSet(sql);
            while(rowset.next()){
                //if no database issues, map the rowset properties to a collection obj and add the obj to the list
                collection = (mapRowToCollection(rowset));
                String username = jdbcTemplate.queryForObject(userSql, String.class, collection.getId());
                dto = mapRowToCollectionsDto(collection,username);
                collectionList.add(dto);
            }
        }catch (CannotGetJdbcConnectionException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            throw new RuntimeException("Unable to connect to the database!", e);
        } catch (BadSqlGrammarException e) {
            // catch any SQL command errors and throw a new error to be caught at next level
            throw new RuntimeException("Bad SQL grammar: " + e.getSql() + "\n" + e.getSQLException(), e);
        } catch (DataIntegrityViolationException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            throw new RuntimeException("Database Integrity Violation!", e);
        }
        //return the whole collections data base
        return collectionList;
    }

    @Override
    public List<Collection> getCollectionsByTCG(int tcgId) {
        List<Collection> collectionList = new ArrayList<>();
        String sql = "select * from collections where tcg_id = ?;";
        try{
            SqlRowSet rowset = jdbcTemplate.queryForRowSet(sql,tcgId);
            while(rowset.next()){
                collectionList.add(mapRowToCollection(rowset));
            }
        }catch (CannotGetJdbcConnectionException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            throw new RuntimeException("Unable to connect to the database!", e);
        } catch (BadSqlGrammarException e) {
            // catch any SQL command errors and throw a new error to be caught at next level
            throw new RuntimeException("Bad SQL grammar: " + e.getSql() + "\n" + e.getSQLException(), e);
        } catch (DataIntegrityViolationException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            throw new RuntimeException("Database Integrity Violation!", e);
        }
        return collectionList;
    }

    @Override
    public List<Collection> getAllUserCollections(String username) {
        List<Collection> collectionList = new ArrayList<>();
        String sql = "select * from collections join collections_user on " +
                "collections.collection_id = collections_user.collection_id where user_id = ?;";
        try{
          int userId = userDao.findIdByUsername(username);
          SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
           while(rowSet.next()){
               collectionList.add(mapRowToCollection(rowSet));
           }
        }catch (CannotGetJdbcConnectionException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            throw new RuntimeException("Unable to connect to the database!", e);
        } catch (BadSqlGrammarException e) {
            // catch any SQL command errors and throw a new error to be caught at next level
            throw new RuntimeException("Bad SQL grammar: " + e.getSql() + "\n" + e.getSQLException(), e);
        } catch (DataIntegrityViolationException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            throw new RuntimeException("Database Integrity Violation!", e);
        }
        return collectionList;
    }

    @Override
    public List<Collection> getUserCollectionsByTCG(String username, int tcgId) {
        List<Collection> collectionList = new ArrayList<>();
        String sql = "select * from collections join collections_user on " +
                "collections.collection_id = collections_user.collection_id where user_id = ? and tcg_id = ?;";
        try{
            int userId = userDao.findIdByUsername(username);
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId, tcgId);
            while(rowSet.next()){
                collectionList.add(mapRowToCollection(rowSet));
            }
        }catch (CannotGetJdbcConnectionException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            throw new RuntimeException("Unable to connect to the database!", e);
        } catch (BadSqlGrammarException e) {
            // catch any SQL command errors and throw a new error to be caught at next level
            throw new RuntimeException("Bad SQL grammar: " + e.getSql() + "\n" + e.getSQLException(), e);
        } catch (DataIntegrityViolationException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            throw new RuntimeException("Database Integrity Violation!", e);
        }
        return collectionList;
    }

    @Override///////LOOOK AT SOMETHING HERE////////NEED USER ID TO GET ONe RECORD//////
    public Card getCollectionEntryByIds(int collection_id, String cardId) {
        Card card = null;
        String sql = "select * from cards join collections_cards on cards.card_id = collections_cards.card_id where collection_id =? and card_id = ?";
        try{
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql,collection_id, cardId);
            if(rowSet.next()){
               card = cardDao.mapResultsToCard(rowSet);
            }
        }catch (CannotGetJdbcConnectionException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            throw new RuntimeException("Unable to connect to the database!", e);
        } catch (BadSqlGrammarException e) {
            // catch any SQL command errors and throw a new error to be caught at next level
            throw new RuntimeException("Bad SQL grammar: " + e.getSql() + "\n" + e.getSQLException(), e);
        } catch (DataIntegrityViolationException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            throw new RuntimeException("Database Integrity Violation!", e);
        }
        return card;
    }

    @Override
    public List<Card> getCollectionEntryByQty(int qty, int collectionId) {
        List<Card> cards = new ArrayList<>();
        String sql = "select card_id from collections_cards where collection_id = ? and quantity = ?;";
        try{
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql,collectionId,qty);
            while(rowSet.next()){
                String cardsql = "select * from cards where card_id = ?;";
                SqlRowSet result = jdbcTemplate.queryForRowSet(cardsql, rowSet);
                if(result.next()){
                    cards.add(cardDao.mapResultsToCard(rowSet));
                }
            }
        }catch (CannotGetJdbcConnectionException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            throw new RuntimeException("Unable to connect to the database!", e);
        } catch (BadSqlGrammarException e) {
            // catch any SQL command errors and throw a new error to be caught at next level
            throw new RuntimeException("Bad SQL grammar: " + e.getSql() + "\n" + e.getSQLException(), e);
        } catch (DataIntegrityViolationException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            throw new RuntimeException("Database Integrity Violation!", e);
        }
        return cards;
    }

    @Override
    public List<CardDto> getCardsByCollectionId(int collectionId) {
        Card card;
        CardDto cardDto;
        List<CardDto> cards = new ArrayList<>();
        String sql = "select card_id, quantity from collections_cards where collection_id = ?;";
        try{
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql,collectionId);
            while(rowSet.next()){
                card = new Card();
                cardDto = new CardDto();
                String id = rowSet.getString("card_id");
                int qty = rowSet.getInt("quantity");
                String cardsql = "select * from cards where card_id = ?;";
                SqlRowSet result = jdbcTemplate.queryForRowSet(cardsql,id);
                if(result.next()){
                    card = cardDao.mapResultsToCard(result);
                }
                cardDto.setQty(qty);
                cardDto.setCmc(card.getCmc());
                cardDto.setCollectorNumber(card.getCollectorNumber());
                cardDto.setColorIdentity(card.getColorIdentity());
                cardDto.setColors(card.getColors());
                cardDto.setEdhrecRank(card.getEdhrecRank());
                cardDto.setId(id);
                cardDto.setImageUrl(card.getImageUrl());
                cardDto.setLayout(card.getLayout());
                cardDto.setLegalities(card.getLegalities());
                cardDto.setName(card.getName());
                cardDto.setReverseImgUrl(card.getReverseImgUrl());
                cardDto.setScryfallUrl(card.getScryfallUrl());
                cardDto.setSetCode(card.getSetCode());
                cardDto.setSetName(card.getSetName());
                cardDto.setSmallImgUrl(card.getSmallImgUrl());
                cardDto.setSmallReverseImgUrl(card.getSmallReverseImgUrl());
                cardDto.setTcgId(card.getTcgId());
                cards.add(cardDto);
            }
        }catch (CannotGetJdbcConnectionException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            throw new RuntimeException("Unable to connect to the database!", e);
        } catch (BadSqlGrammarException e) {
            // catch any SQL command errors and throw a new error to be caught at next level
            throw new RuntimeException("Bad SQL grammar: " + e.getSql() + "\n" + e.getSQLException(), e);
        } catch (DataIntegrityViolationException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            throw new RuntimeException("Database Integrity Violation!", e);
        }
        return cards;
    }

    @Override
    public int addCollection(Collection collection, String username) {
        String sql = "insert into collections (collection_name, tcg_id) values (?, ?) returning collection_id;";
        String connectingSql = "insert into collections_user (collection_id, user_id) values (?,?);";
        int newCollectionId;
        try{
            int userId = userDao.findIdByUsername(username);
            newCollectionId = jdbcTemplate.queryForObject(sql, int.class,collection.getName(),collection.getTcgId());
            int check = jdbcTemplate.update(connectingSql,newCollectionId,userId);
            if(check != 1){
                throw new RuntimeException("Failed to manipulate the database!");
            }
        }catch (CannotGetJdbcConnectionException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            throw new RuntimeException("Unable to connect to the database!", e);
        } catch (BadSqlGrammarException e) {
            // catch any SQL command errors and throw a new error to be caught at next level
            throw new RuntimeException("Bad SQL grammar: " + e.getSql() + "\n" + e.getSQLException(), e);
        } catch (DataIntegrityViolationException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            throw new RuntimeException("Database Integrity Violation!", e);
        }
        return newCollectionId;
    }

    @Override
    public int removeCollection(int collectionId) {
        String CCsql = "delete from collections_cards where collection_id = ?;";
        String CUsql = "delete from collections_user where collection_id = ?;";
        String Csql = "delete from collections where collection_id = ?;";
        int check =-1;
        try{
            check = jdbcTemplate.update(CCsql,collectionId);
            if(check == -1){
                throw new RuntimeException("Failed to manipulate the database!");
            }else{
                check = jdbcTemplate.update(CUsql,collectionId);
                if(check ==0){
                    throw new RuntimeException("Failed to manipulate the database!");
                }else{
                    check = jdbcTemplate.update(Csql,collectionId);
                    if(check != 1){
                        throw new RuntimeException("Failed to manipulate the database!");
                    }
                }
            }
        }catch (CannotGetJdbcConnectionException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            throw new RuntimeException("Unable to connect to the database!", e);
        } catch (BadSqlGrammarException e) {
            // catch any SQL command errors and throw a new error to be caught at next level
            throw new RuntimeException("Bad SQL grammar: " + e.getSql() + "\n" + e.getSQLException(), e);
        } catch (DataIntegrityViolationException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            throw new RuntimeException("Database Integrity Violation!", e);
        }
        return check;
    }

    @Override
    public int addCardToCollection(Card card, int collectionId) {
        int qty =1;
        String sql = "insert into collections_cards (collection_id, card_id, quantity) values(?,?,?);";
        String update = "update collections_cards set quantity = ? where card_id =? and collection_id =?";
        String quansql = "select quantity from collections_cards where card_id =? and collection_id =?";
        int check = -1;
        try{
            check = jdbcTemplate.update(sql,collectionId,card.getId(),qty);
            if(check == -1){
                throw new RuntimeException("Failed to manipulate the database!");
            }
        }catch (CannotGetJdbcConnectionException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            throw new RuntimeException("Unable to connect to the database!", e);
        } catch (BadSqlGrammarException e) {
            // catch any SQL command errors and throw a new error to be caught at next level
            throw new RuntimeException("Bad SQL grammar: " + e.getSql() + "\n" + e.getSQLException(), e);
        } catch (DataIntegrityViolationException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            qty = jdbcTemplate.queryForObject(quansql,int.class,card.getId(),collectionId);
            qty++;
            jdbcTemplate.update(update,qty,card.getId(),collectionId);
            throw new RuntimeException("Database Integrity Violation!", e);
        }
        return check;
    }


        public void deleteCardFromCollection(String cardId, int collectionId){
            String sql ="delete from collections_cards where card_id = ? and collection_id =? ;";
            try{
                int check = jdbcTemplate.update(sql, cardId,collectionId);
                if(check!=1){
                    throw new RuntimeException("Failed to manipulate the database!");
                }
            }catch (CannotGetJdbcConnectionException e) {
                // catch any database connection errors and throw a new error to be caught at next level
                throw new RuntimeException("Unable to connect to the database!", e);
            } catch (BadSqlGrammarException e) {
                // catch any SQL command errors and throw a new error to be caught at next level
                throw new RuntimeException("Bad SQL grammar: " + e.getSql() + "\n" + e.getSQLException(), e);
            } catch (DataIntegrityViolationException e) {
                // catch any database connection errors and throw a new error to be caught at next level
                throw new RuntimeException("Database Integrity Violation!", e);
            }
        }

        @Override
        public Collection getCollectionById(int collectionId) {
        Collection collection = new Collection();
        String sql ="select * from collections where collection_id = ?";
        try{
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql,collectionId);
            if(result.next()){
                collection = mapRowToCollection(result);
            }
        }catch (CannotGetJdbcConnectionException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            throw new RuntimeException("Unable to connect to the database!", e);
        } catch (BadSqlGrammarException e) {
            // catch any SQL command errors and throw a new error to be caught at next level
            throw new RuntimeException("Bad SQL grammar: " + e.getSql() + "\n" + e.getSQLException(), e);
        } catch (DataIntegrityViolationException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            throw new RuntimeException("Database Integrity Violation!", e);
        }
        return collection;
    }
    public User getUserForCollectionId(int collectionID){
        User user = new User();

        String sql = "select user_id from collections_user where collection_id = ?;";
        try{
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql,collectionID);
            if(result.next()){
                int userId = result.getInt("user_id");
                user = userDao.getUserById(userId);
            }
        }catch (CannotGetJdbcConnectionException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            throw new RuntimeException("Unable to connect to the database!", e);
        } catch (BadSqlGrammarException e) {
            // catch any SQL command errors and throw a new error to be caught at next level
            throw new RuntimeException("Bad SQL grammar: " + e.getSql() + "\n" + e.getSQLException(), e);
        } catch (DataIntegrityViolationException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            throw new RuntimeException("Database Integrity Violation!", e);
        }
        return user;
    }


    private Collection mapRowToCollection(SqlRowSet set){
        Collection collection = new Collection();
        collection.setId(set.getInt("collection_id"));
        collection.setName(set.getString("collection_name"));
        collection.setTcgId(set.getInt("tcg_id"));
        return collection;
    }

    private CollectionsDto mapRowToCollectionsDto(Collection col, String username){
        CollectionsDto collection = new CollectionsDto();
        collection.setCollectionId(col.getId());
        collection.setCollectionName(col.getName());
        collection.setTcgId(col.getTcgId());
        collection.setUsername(username);
        return collection;
    }

}

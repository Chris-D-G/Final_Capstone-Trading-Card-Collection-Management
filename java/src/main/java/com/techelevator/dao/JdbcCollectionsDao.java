package com.techelevator.dao;

import com.techelevator.model.Card;
import com.techelevator.model.Collection;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

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
    public List<Collection> getAllCollections() {
        //new list
        List<Collection> collectionList = new ArrayList<>();
        //grab all collections from database
        String sql = "select * from collections;";
        try{
            SqlRowSet rowset = jdbcTemplate.queryForRowSet(sql);
            while(rowset.next()){
                //if no database issues, map the rowset properties to a collection obj and add the obj to the list
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
               card = mapResultsToCard(rowSet);
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
                    cards.add(mapResultsToCard(result));
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
    public List<Card> getCardsByCollectionId(int collectionId) {
        List<Card> cards = new ArrayList<>();
        String sql = "select card_id from collections_cards where collection_id = ?;";
        try{
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql,collectionId);
            while(rowSet.next()){
                String id = rowSet.getString("card_id");
                String cardsql = "select * from cards where card_id = ?;";
                SqlRowSet result = jdbcTemplate.queryForRowSet(cardsql,id);
                if(result.next()){
                    cards.add(mapResultsToCard(result));
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
        String CCsql = "delete from collections_cards where collection_id =?;";
        String CUsql = "delete from collections_user where collection_id = ?;";
        String Csql = "delete from collections where collection_id = ?;";
        int check =-1;
        try{
            check = jdbcTemplate.update(CCsql);
            if(check == -1){
                throw new RuntimeException("Failed to manipulate the database!");
            }else{
                check = jdbcTemplate.update(CUsql);
                if(check ==0){
                    throw new RuntimeException("Failed to manipulate the database!");
                }else{
                    check = jdbcTemplate.update(Csql);
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
            throw new RuntimeException("Database Integrity Violation!", e);
        }
        return check;
    }


    private Collection mapRowToCollection(SqlRowSet set){
        Collection collection = new Collection();
        collection.setId(set.getInt("collection_id"));
        collection.setName(set.getString("collection_name"));
        collection.setTcgId(set.getInt("tcg_id"));
        return collection;
    }
    //ToDO remove this method and make a call to the cardDAO method
    public Card mapResultsToCard(SqlRowSet results) {
        Card mappedCard = new Card();
        mappedCard.setId(results.getString("card_id"));
        mappedCard.setTcgId(results.getInt("tcg_id"));
        mappedCard.setName(results.getString("card_title"));
        mappedCard.setSmallImgUrl(results.getString("card_small_image_url"));
        mappedCard.setImageUrl(results.getString("card_normal_image_url"));
        mappedCard.setScryfallUrl(results.getString("card_details_url"));
        return mappedCard;
    }


}

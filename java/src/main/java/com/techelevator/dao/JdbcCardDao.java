package com.techelevator.dao;

import com.techelevator.model.Card;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

//Define this class as an Java Database Connection implementation of a CardDao
@Component
public class JdbcCardDao implements CardDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcCardDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Card getCardById(String cardID) {
        // query command to select card using an exact cardID using parameterized input
        String sql = "SELECT card_id,tcg card_title, card_small_image_url,card_normal_image_url, card_details_url " +
                "FROM cards WHERE card_id = ?;";
        // queried card initially set to null in case no results are returned
        Card queriedCard = null;
        // setting up jdbc call inside of a try block to catch any potential errors
        try {
            // send SQL command and return the results as a SQL Row Set
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cardID);
            // if there is a RowSet returned...
            if (results.next()) {
                // use helper method to map sql row to card object
                queriedCard = mapResultsToCard(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            throw new RuntimeException("Unable to connect to the database!", e);
        } catch (BadSqlGrammarException e) {
            // catch any SQL command errors and throw a new error to be caught at next level
            throw new RuntimeException("Bad SQL grammar: " + e.getSql() + "\n" + e.getSQLException(), e);
        } catch (DataIntegrityViolationException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            throw new RuntimeException("Database Integrity Violation!", e);
        }
        //if there was a valid result, this returns a card object with data otherwise a null is returned
        return queriedCard;
    }

    @Override
    public Card getCardByTitle(String cardTitle) {
        // query command to select card using an exact card title using parameterized input
        String sql = "SELECT card_id,tcg card_title,card_small_image_url,card_normal_image_url, card_details_url " +
                "FROM cards WHERE card_title = ?;";
        // queried card initially set to null in case no results are returned
        Card queriedCard = null;
        // setting up jdbc call inside of a try block to catch any potential errors
        try {
            // send SQL command and return the results as a SQL Row Set
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cardTitle);
            // if there is a RowSet returned...
            if (results.next()) {
                // use helper method to map sql row to card object
                queriedCard = mapResultsToCard(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            throw new RuntimeException("Unable to connect to the database!", e);
        } catch (BadSqlGrammarException e) {
            // catch any SQL command errors and throw a new error to be caught at next level
            throw new RuntimeException("Bad SQL grammar: " + e.getSql() + "\n" + e.getSQLException(), e);
        } catch (DataIntegrityViolationException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            throw new RuntimeException("Database Integrity Violation!", e);
        }
        //if there was a valid result, this returns a card object with data otherwise a null is returned
        return queriedCard;
    }

    @Override
    public List<Card> getCardsByTitle(String cardTitle) {
        // Queried Cards set to empty list
        List<Card> queriedCards = new ArrayList<>();
        String parameter = "%"+ cardTitle+"%";
        // query command to select card matches using a card title as parameterized input
        String sql = "SELECT card_id,tcg card_title, card_small_image_url,card_normal_image_url, card_details_url " +
                     "FROM cards WHERE card_title ILIKE ?;";
        try{
            // send SQL command and return the results as a SQL Row Set
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql,parameter);
            // while there are results
            while(results.next()){
                // add the mapped results to the list
                queriedCards.add(mapResultsToCard(results));
            }
        }catch (CannotGetJdbcConnectionException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            throw new RuntimeException("Unable to connect to the database!", e);
        }catch (BadSqlGrammarException e) {
            // catch any SQL command errors and throw a new error to be caught at next level
            throw new RuntimeException("Bad SQL grammar: " + e.getSql() + "\n" + e.getSQLException(), e);
        }catch (DataIntegrityViolationException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            throw new RuntimeException("Database Integrity Violation!", e);
        }
        //if there were valid results, this returns a list of card object with data otherwise an empty list is returned
        return queriedCards;
    }
    @Override
    public Card addCard(Card cardToBeAdded) {
        // Setting initially created card
        Card createdCard = null;
        //query command to insert card into the database
        String sql = "INSERT INTO cards (card_id, tcg_id, card_title,card_small_image_url,card_normal_image_url, card_details_url) "+
                                 "VALUES(?, ?, ?, ?, ?, ?)";
    try{
        int rowsInserted = jdbcTemplate.update(sql,cardToBeAdded.getId(),
                                cardToBeAdded.getTcgId(),
                                cardToBeAdded.getName(),
                                cardToBeAdded.getSmallImgUrl(),
                                cardToBeAdded.getImageUrl(),
                                cardToBeAdded.getScryfallUrl());
        if(rowsInserted==1){
            createdCard = getCardById(cardToBeAdded.getId());
        }else{
            throw new DataIntegrityViolationException("More that 1 entry updated!");
        }
    }catch (CannotGetJdbcConnectionException e) {
        // catch any database connection errors and throw a new error to be caught at next level
        throw new RuntimeException("Unable to connect to the database!", e);
    }catch (BadSqlGrammarException e) {
        // catch any SQL command errors and throw a new error to be caught at next level
        throw new RuntimeException("Bad SQL grammar: " + e.getSql() + "\n" + e.getSQLException(), e);
    }catch (DataIntegrityViolationException e) {
        // catch any database connection errors and throw a new error to be caught at next level
        throw new RuntimeException("Database Integrity Violation!", e);
    }

        return createdCard;
    }


    /**
     * Method that maps sql results to card object
     *
     * @param results SQL row set  returned by database
     * @return Card object
     */
    public Card mapResultsToCard(SqlRowSet results) {
        Card mappedCard = new Card();
        mappedCard.setId(results.getString("card_id"));
        mappedCard.setTcgId(results.getInt("tcg_id"));
        mappedCard.setName(results.getString("card_title"));
        mappedCard.setSmallImageUrl.getString("card_small_image_url");
        mappedCard.setImageUrl(results.getString("card_normal_image_url"));
        mappedCard.setScryfallUrl(results.getString("card_details_url"));
        return mappedCard;
    }


}

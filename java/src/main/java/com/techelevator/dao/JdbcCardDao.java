package com.techelevator.dao;

import com.techelevator.model.Card;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import java.util.List;

//Define this class as an Java Database Connection implementation of CardDao
@Component
public class JdbcCardDao implements CardDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcCardDao (JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Card getCardById(String cardID) {
        // query command to select card using an exact cardID using parameterized input
        String sql = "SELECT card_id,tcg card_title, card_image_url, card_details_url "+
                     "FROM cards WHERE card_id = ?;";
        // queried card initially set to null in case no results are returned
        Card queriedCard = null;
        // setting up jdbc call inside of a try block to catch any potential errors
        try{
            // send SQL command and return the results as a SQL Row Set
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql,cardID);
            // if there is a RowSet returned...
            if(results.next()){
                queriedCard = mapResultsToCard(results);
            }

        }catch (CannotGetJdbcConnectionException e){
            throw new RuntimeException("Unable to connect to the database!", e);
        }catch(BadSqlGrammarException e){
            throw new RuntimeException("Bad SQL grammar: " + e.getSql() + "\n"+ e.getSQLException(), e);
        }catch (DataIntegrityViolationException e){
            throw new RuntimeException("Database Integrity Violation!",e);
        }


        return queriedCard;
    }

    @Override
    public Card getCardByTitle(String cardTitle) {
        return null;
    }

    @Override
    public List<Card> getCardsByTitle(String cardTitle) {
        return null;
    }


    /**
     * Method that maps sql results to card object
     * @param results SQL row set  returned by database
     * @return Card object
     */
    private Card mapResultsToCard (SqlRowSet results){
        Card mappedCard = new Card();
        mappedCard.setId(results.getString("card_id"));
        mappedCard.setTcgId(results.getInt("tcg_id"));
        mappedCard.setName(results.getString("card_title"));
        mappedCard.setImageUrl(results.getString("card_image_url"));
        mappedCard.setScryfallUrl(results.getString("card_details_url"));
        return mappedCard;
    }



}

package com.techelevator.dao;

import com.techelevator.model.Card;
import com.techelevator.model.Collection;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

public class JdbcCollectionsDao implements CollectionsDao{


    private UserDao userDao;
    private JdbcTemplate jdbcTemplate;

    public JdbcCollectionsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        userDao = new JdbcUserDao(jdbcTemplate);
    }


    @Override
    public List<Collection> getAllCollections() {
        List<Collection> collectionList = new ArrayList<>();
        String sql = "select * from collections;";
        try{
            SqlRowSet rowset = jdbcTemplate.queryForRowSet(sql);
            while(rowset.next()){
                collectionList.add(mapRowToCollection(rowset));
            }
        }catch(CannotGetJdbcConnectionException | DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
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
        }catch(CannotGetJdbcConnectionException | DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
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
        }catch(CannotGetJdbcConnectionException | DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
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
        }catch(CannotGetJdbcConnectionException | DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return collectionList;
    }

    @Override
    public Collection getCollectionEntryByIds(int collection_id, String cardId) {
        String sql = "select * from collections where collection_id =? and card_id = ?";
        Collection collection;
        try{
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql,collection_id, cardId);
            if(rowSet.next()){

            }
        }catch(CannotGetJdbcConnectionException | DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    @Override
    public List<Collection> getCollectionEntryByQty(int qty) {
        return null;
    }

    @Override
    public List<Card> getCardsByCollectionId(int collectionId) {
        return null;
    }

    @Override
    public int addCollection(Collection collection) {
        return 0;
    }

    @Override
    public int removeCollectionEntry(int collectionId) {
        return 0;
    }

    @Override
    public int removeWholeCollection(String username, int collectionId) {
        return 0;
    }

    public Collection mapRowToCollection(SqlRowSet set){
        Collection collection = new Collection();
        collection.setId(set.getInt("collection_id"));
        collection.setName(set.getString("collection_name"));
        collection.setTcgId(set.getInt("tcg_id"));
        return collection;
    }

}

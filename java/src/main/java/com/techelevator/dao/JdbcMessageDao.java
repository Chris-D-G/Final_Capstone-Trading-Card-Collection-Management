package com.techelevator.dao;

import com.techelevator.model.Message;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcMessageDao implements MessageDao
{
    private final JdbcTemplate jdbcTemplate;

    public JdbcMessageDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Message sendNewMessage(Message messageToSend) {

        String sender = messageToSend.getMessageSender();
        String receiver = messageToSend.getMessageReceiver();
        String messageBody = messageToSend.getMessageText();

        Message createdMessage = null;

        String sql = "INSERT INTO messages (message_sender_user_id, message_receiver_user_id, " +
                "message_text, message_timestamp, message_read_status) " +
                "VALUES ( (SELECT user_id FROM users WHERE username = ? ), " +
                "(SELECT user_id FROM users WHERE username = ?), " +
                "?, " +
                "CURRENT_TIMESTAMP(0), " +
                "false) " +
                "RETURNING message_id;";
        try {

            int messageID = jdbcTemplate.update(sql, sender, receiver, messageBody, int.class);
            // need a method to retrieve a single message from the database when given an ID #
            createdMessage = getMessageByID(messageID);

        }  catch (CannotGetJdbcConnectionException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            throw new RuntimeException("Unable to connect to the database!", e);
        } catch (BadSqlGrammarException e) {
            // catch any SQL command errors and throw a new error to be caught at next level
            throw new RuntimeException("Bad SQL grammar: " + e.getSql() + "\n" + e.getSQLException(), e);
        } catch (DataIntegrityViolationException e) {
            // catch any database connection errors and throw a new error to be caught at next level
            throw new RuntimeException("Database Integrity Violation!", e);
        }

        return createdMessage;
    }

    @Override
    public Message getMessageByID (int messageID){

        Message messageToRetrieve = null;

        String sql = "SELECT message_id, user1.username = Sender, user2.username = Receiver, " +
                "message_text, message_timestamp, message_read_status " +
                "FROM messages " +
                "JOIN users AS user1 ON messages.message_sender_user_id = user1.user_id " +
                "JOIN users AS user2 ON messages.message_sender_user_id = user2.user_id " +
                "WHERE message_id = ?;";

        try {
            SqlRowSet message = jdbcTemplate.queryForRowSet(sql, messageID);

            if (message.next()) {
                messageToRetrieve = mapRowToMessage(message);
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

        return messageToRetrieve;
    }



    @Override
    public List<Message> retrieveMessages(String username) {

        List<Message> allMessages = new ArrayList<>();

        String sql = "SELECT message_id, user1.username AS Sender, user2.username AS Receiver, " +
                "message_text, message_timestamp, message_read_status FROM messages " +
                "JOIN users AS user1 ON messages.message_sender_user_id = u1.user_id " +
                "JOIN users AS user2 ON messages.message_receiver_user_id = u2.user_id " +
                "WHERE sender = ? OR receiver = ? " +
                "ORDER BY message_timestamp DESC;";

        try {
            SqlRowSet messages = jdbcTemplate.queryForRowSet(sql, username);
            while (messages.next()) {
                allMessages.add(mapRowToMessage(messages));
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

        return allMessages;
    }



    @Override
    public boolean deleteMessage(int messageId) {

        int numberOfRows = 0;

        String sql = "DELETE FROM messages WHERE message_id = ?;";

        try {
            numberOfRows = jdbcTemplate.update(sql, messageId);
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

        if (numberOfRows == 0){
            return true;
        } else {
            return false; }
    }


    private Message mapRowToMessage(SqlRowSet results){
        Message message = new Message();

        message.setMessageID(results.getInt("message_id"));
        message.setMessageText(results.getString("message_text"));
        message.setMessageSender(results.getString("Sender"));
        message.setMessageReceiver(results.getString("Receiver"));
        message.setMessageTimestamp(results.getTimestamp("message_timestamp").toLocalDateTime());
        message.setRead(results.getBoolean("message_read_status"));

        return message;
    }
}

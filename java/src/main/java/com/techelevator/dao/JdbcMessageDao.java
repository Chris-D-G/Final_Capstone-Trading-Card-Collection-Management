package com.techelevator.dao;

import com.techelevator.model.Message;
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

        String sql = "INSERT INTO messages (message_sender_user_id, message_receiver_user_id, " +
                "message_text, message_timestamp, message_read_status) " +
                "VALUES ( (SELECT user_id FROM users WHERE username = ? ), " +
                "(SELECT user_id FROM users WHERE username = ?), " +
                "?, CURRENT_TIMESTAMP(0), false) " +
                "RETURNING message_id;";

        return null;
    }

    @Override
    public List<Message> retrieveMessages(String username) {
        return null;
    }

    @Override
    public boolean deleteMessage(int messageId) {

        String sql = "DELETE * FROM messages"

        return false;
    }

//    @Override
//    public List<Message> getMessagesByUsersID(int usersID, int receiversID) {
//        List<Message> conversation = new ArrayList<>();
//
//        String sql = "SELECT message_text, message_timestamp, users.username " +
//                "FROM messages " +
//                "JOIN users ON users.user_id = messages.message_sender_user_id " +
//                "WHERE message_sender_user_id = ? AND message_receiver_user_id = ? " +
//                "ORDER BY message_timestamp ASC;";
//
//        SqlRowSet messages = jdbcTemplate.queryForRowSet(sql, usersID, receiversID);
//        while (messages.next()) {
//            conversation.add(mapRowToMessage(messages));
//        }
//
//        return conversation;
//    }

//    private Message mapRowToMessage(SqlRowSet results){
//        Message message = new Message();
//
//        message.setMessageID(results.getInt("message_id"));
//        message.setMessageText(results.getString("message_text"));
//        message.setMessageSenderID(results.getInt("message_sender_user_id"));
//        message.setMessageReceiverID(results.getInt("message_receiver_user_id"));
//        message.setMessageTimestamp(results.getTimestamp("message_timestamp").toLocalDateTime());
//
//        return message;
//    }
}

package com.techelevator.dao;

import com.techelevator.model.Message;
import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

//This class runs the testing for the JDBC MessageDAO methods
public class JdbcMessageDaoTests extends BaseDaoTests {
    // sut => System Under Test
    private JdbcMessageDao sut;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcMessageDao(jdbcTemplate);
    }

    @Test
    public void send_message_returns_new_message_with_all_fields_completed(){
        Message messageToSend = new Message();
        messageToSend.setMessageText("Test Message #1");
        messageToSend.setMessageSender("user1");
        messageToSend.setMessageReceiver("user2");

    }














}

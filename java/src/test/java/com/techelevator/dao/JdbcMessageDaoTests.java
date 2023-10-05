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
        Message expected = new Message();
        expected.setMessageText("Test Message #1");
        expected.setMessageSender("user1");
        expected.setMessageReceiver("user2");


        Message messageToSend = new Message();
        messageToSend.setMessageText("Test Message #1");
        messageToSend.setMessageSender("user1");
        messageToSend.setMessageReceiver("user2");

        Message actual = sut.sendNewMessage(messageToSend);

        assertEqualMessage(expected,actual);

    }

    /**
     * Helper method that compares two message objects
     *
     * @param expected message that is given to S.U.T.
     * @param actual message that is returned by S.U.T.
     */
    private void assertEqualMessage(Message expected, Message actual){
        Assert.assertTrue("Message ID should be provided by the database", actual.getMessageID()>0);
        Assert.assertEquals("Message text bodies should be the same", expected.getMessageText(), actual.getMessageText());
        Assert.assertEquals("Message sender names should be the same", expected.getMessageSender(), actual.getMessageSender());
        Assert.assertEquals("Message receiver names should be the same",expected.getMessageReceiver(),actual.getMessageReceiver());
        Assert.assertNotNull("Timestamp should not be null",actual.getMessageTimestamp());
        Assert.assertFalse("Message read status should be the initially set to false",actual.isRead());

    }












}

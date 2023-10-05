package com.techelevator.dao;

import com.techelevator.model.Message;
import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.springframework.dao.DataIntegrityViolationException;
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

    /**
     * Method that checks the creation of a new message
     */
    @Test
    public void send_message_returns_new_message_with_all_fields_completed(){
        // Test 1 of 2
        // create expected test message that to check returned message against
        Message expected = new Message();
        // setting various properties
        expected.setMessageText("Test Message #1");
        expected.setMessageSender("user1");
        expected.setMessageReceiver("user2");
        // create message to feed into database
        Message messageToSend = new Message();
        // setting various properties
        messageToSend.setMessageText("Test Message #1");
        messageToSend.setMessageSender("user1");
        messageToSend.setMessageReceiver("user2");
        // send message to the database and set returned object to actual Message
        Message actual = sut.sendNewMessage(messageToSend);
        //compare the two message objects
        assertEqualMessage(expected,actual);
        // Test 2 of 2
        // create expected test message that to check returned message against
        expected = new Message();
        // setting various properties
        expected.setMessageText("Test Message #2");
        expected.setMessageSender("user3");
        expected.setMessageReceiver("user2");
        // create message to feed into database
        messageToSend = new Message();
        // setting various properties
        messageToSend.setMessageText("Test Message #2");
        messageToSend.setMessageSender("user3");
        messageToSend.setMessageReceiver("user2");
        // send message to the database and set returned object to actual Message
        actual = sut.sendNewMessage(messageToSend);
        //compare the two message objects
        assertEqualMessage(expected,actual);
    }

    @Test
    public void get_message_by_id_returns_expected_message(){
        Message expected = null;
        Message actual = sut.getMessageByID(1);


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

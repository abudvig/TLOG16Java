/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlog16java;

import java.time.LocalTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import timelogger.exceptions.NotExpectedTimeOrderException;

/**
 *
 * @author Szandi
 */
public class TaskTest {

    public TaskTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Starting Task class's tests");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("Finished Task class's tests");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test(expected = NotExpectedTimeOrderException.class)
    public void testWrongStartTimeEndTimeOrder() throws NotExpectedTimeOrderException {
        System.out.println("Start time 08:45 | End time 07:30");
        Task instance = new Task("1234", "comment", "08:45", "07:30");
    }
    
    @Test
    public void testModifiedEndTime() throws NotExpectedTimeOrderException{
        System.out.println("07:30 - 07:45: end time modified to 08:00");
        Task instance = new Task("1234", "comment", "07:30", "07:45");
        instance.setEndTime("08:00");
        LocalTime expected = LocalTime.of(8,0);
        assertEquals(expected, instance.endTime);
    }
    
    @Test
    public void testEmptyComment() throws NotExpectedTimeOrderException{
        System.out.println("Empty comment");
        Task instance = new Task("1234", "", "07:30", "07:45");
        String expected = "";
        assertEquals(expected, instance.comment);
    }

}

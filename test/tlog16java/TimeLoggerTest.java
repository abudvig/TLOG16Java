/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlog16java;

import java.util.ArrayList;
import java.util.List;
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
public class TimeLoggerTest {

    public TimeLoggerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Starting TimeLogger class's tests");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("Finished TimeLogger class's tests");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

//    Create a WorkDay with actual day 2016.04.14!
//    Create a 2016/04 WorkMonth!
//    Create a Task with beginning time 7:30, finishing time 10:30!
//    Add the task to the day, add the day to the month!
//    Create a new TimeLogger!
//    Add the month to the timelogger!
//    Call the getMinPerTask on the Task!
//    Call the getSumPerMonth on the first month of the timelogger!
//    -> These should be equal.
    @Test
    public void testMinPerTaskAndSumPerMonth() throws NotExpectedTimeOrderException {
        System.out.println("getMinPerTask and getSumPerMonth are equal");

        List<Task> tasks = new ArrayList<>();
        WorkDay newWorkDay = new WorkDay(tasks, 2016, 4, 14, 0);
        
        List<WorkDay> days = new ArrayList<>();
        WorkMonth newWorkMonth = new WorkMonth(days, 2016, 4, 0, 0);
        
        Task newTask = new Task("1234", "comment", "07:30", "10:30");
        newWorkDay.addTask(newTask);
        newWorkMonth.addWorkDay(newWorkDay);
        
        TimeLogger newTimeLogger = new TimeLogger();
        newTimeLogger.addMonth(newWorkMonth);

        long getMinPerTask = newTask.getMinPerTask();
        long getSumPerMonth = newTimeLogger.getMonths().get(0).getSumPerMonth();
        assertEquals(getMinPerTask, getSumPerMonth);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlog16java;

import java.time.YearMonth;
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
public class WorkMonthTest {
    
    public WorkMonthTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Starting WorkMonth class's tests");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("Finished WorkMonth class's tests");
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSumPerMonth() throws NotExpectedTimeOrderException {
        System.out.println("Sum per month is 135");

        Task newTask1 = new Task("1234", "comment", "07:30", "08:45");
        List<Task> tasks = new ArrayList<>();
        WorkDay newWorkDay1 = new WorkDay(tasks, 420, 0);
        newWorkDay1.addTask(newTask1);

        Task newTask2 = new Task("5678", "comment", "08:45", "09:45");
        WorkDay newWorkDay2 = new WorkDay(tasks, 420, 2016, 9, 1, 0);
        newWorkDay2.addTask(newTask2);

        List<WorkDay> days = new ArrayList<>();
        WorkMonth newWorkMonth = new WorkMonth(days, 2016, 9, 0, 0);

        newWorkMonth.addWorkDay(newWorkDay1);
        newWorkMonth.addWorkDay(newWorkDay2);

        long expected = 135;
        long actual = newWorkMonth.getSumPerMonth();
        assertEquals(expected, actual);
    }
    
}

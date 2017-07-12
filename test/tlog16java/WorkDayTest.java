/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlog16java;

import java.time.LocalDate;
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
public class WorkDayTest {

    public WorkDayTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Starting WorkDay class's tests");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("Finished WorkDay class's tests");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testExtraMinPerDay() throws NotExpectedTimeOrderException {
        System.out.println("Extra min per day is -375");
        Task newTask = new Task("1234", "comment", "07:30", "08:45");
        List<Task> tasks = new ArrayList<>();
        WorkDay instance = new WorkDay(tasks, 0);
        instance.addTask(newTask);
        long expected = -375;
        long actual = instance.getExtraMinPerDay();
        assertEquals(expected, actual);
    }

    @Test
    public void testSumPerDay() throws NotExpectedTimeOrderException {
        System.out.println("Sum per day is 135");
        Task newTask1 = new Task("1234", "comment", "07:30", "08:45");
        Task newTask2 = new Task("5432", "comment", "08:45", "09:45");
        List<Task> tasks = new ArrayList<>();
        WorkDay instance = new WorkDay(tasks, 0);
        instance.addTask(newTask1);
        instance.addTask(newTask2);
        System.out.println(tasks.get(0).startTime + " " + tasks.get(1).startTime);
        long expected = 135;
        long actual = instance.getSumPerDay();
        assertEquals(expected, actual);
    }
}

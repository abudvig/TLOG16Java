/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlog16java;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Szandi
 */
public class WorkDay {

    List<Task> tasks;
    long requiredMinPerDay;
    LocalDate actualDay;
    long sumPerDay;

    public WorkDay(List<Task> tasks, long sumPerDay) {
        this.tasks = tasks;
        this.requiredMinPerDay = 450;
        this.actualDay = LocalDate.now();
        this.sumPerDay = sumPerDay;
    }

    public WorkDay(List<Task> tasks, long requiredMinPerDay, int year, int month, int day, long sumPerDay) {
        this.tasks = tasks;
        this.requiredMinPerDay = requiredMinPerDay;
        this.actualDay = LocalDate.of(year, month, day);
        this.sumPerDay = sumPerDay;
    }

    public WorkDay(List<Task> tasks, int year, int month, int day, long sumPerDay) {
        this.tasks = tasks;
        this.requiredMinPerDay = 450;
        this.actualDay = LocalDate.of(year, month, day);
        this.sumPerDay = sumPerDay;
    }

    public WorkDay(List<Task> tasks, long requiredMinPerDay, long sumPerDay) {
        this.tasks = tasks;
        this.requiredMinPerDay = requiredMinPerDay;
        this.actualDay = LocalDate.now();
        this.sumPerDay = sumPerDay;
    }

    public long getRequiredMinPerDay() {
        return requiredMinPerDay;
    }

    public LocalDate getActualDay() {
        return actualDay;
    }

    public long getSumPerDay() {
        return sumPerDay;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    long getExtraMinPerDay() {
        long extraMinPerDay = requiredMinPerDay - sumPerDay;
        return extraMinPerDay;
    }

    void addTask(Task taskToBeChecked) {
        if (Util.isMultipleQuarterHour(taskToBeChecked.getMinPerTask()) && Util.isSeparatedTime(taskToBeChecked, tasks)) {
            tasks.add(taskToBeChecked);
        } else {
            //to be implemented later
        }
    }

//    boolean isWeekday() {
//        boolean isWeekday = false;
//
//        if ((actualDay.getDayOfWeek() != DayOfWeek.SATURDAY) && (actualDay.getDayOfWeek() != DayOfWeek.SUNDAY)) {
//            isWeekday = true;
//        }
//
//        return isWeekday;
//    }

    public void setRequiredMinPerDay(long requiredMinPerDay) {
        this.requiredMinPerDay = requiredMinPerDay;
    }

    public void setActualDay(int year, int month, int day) {
        this.actualDay = LocalDate.of(year, month, day);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlog16java;

import java.time.LocalTime;
import timelogger.exceptions.NotExpectedTimeOrderException;

/**
 *
 * @author Szandi
 */
public class Task {

    String taskId;
    LocalTime startTime;
    LocalTime endTime;
    String comment;

    public Task(String taskId, String comment, int startHour, int startMin, int endHour, int endMin) throws NotExpectedTimeOrderException {
        this.taskId = taskId;
        this.comment = comment;

        startTime = LocalTime.of(startHour, startMin);
        endTime = LocalTime.of(endHour, endMin);

        checkStartTimeEndTimeOrder();
    }

    public Task(String taskId, String comment, String startTime, String endTime) throws NotExpectedTimeOrderException {
        this.taskId = taskId;
        this.comment = comment;

        int startHour = Integer.parseInt(startTime.substring(0, 2));
        int startMinute = Integer.parseInt(startTime.substring(3));
        this.startTime = LocalTime.of(startHour, startMinute);

        int endHour = Integer.parseInt(endTime.substring(0, 2));
        int endMinute = Integer.parseInt(endTime.substring(3));
        this.endTime = LocalTime.of(endHour, endMinute);

        checkStartTimeEndTimeOrder();
    }

    public Task(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskId() {
        return taskId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getComment() {
        return comment;
    }

    long getMinPerTask() {
        long startTimeInMinutes = (startTime.getHour()) * 60 + startTime.getMinute();
        long endTimeInMinutes = (endTime.getHour()) * 60 + endTime.getMinute();
        long MinPerTask = endTimeInMinutes - startTimeInMinutes;
        return MinPerTask;
    }

    boolean isValidRedmineTaskId() {
        boolean isValidRedmineTaskId = false;

        if (Util.digitCounter(taskId) == 4) {
            if (taskId.length() == 4) {
                isValidRedmineTaskId = true;
            }
        }

        return isValidRedmineTaskId;
    }

    boolean isValidLTTaskId() {
        boolean isValidLTTaskId = false;

        if (Util.digitCounter(taskId) == 4) {
            if (taskId.startsWith("LT-") && taskId.length() == 7) {
                isValidLTTaskId = true;
            }
        }

        return isValidLTTaskId;
    }

    boolean isValidTaskId() {
        boolean isValidTaskId = false;

        if (isValidRedmineTaskId() || isValidLTTaskId()) {
            isValidTaskId = true;
        }

        return isValidTaskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setStartTime(int hour, int min) throws NotExpectedTimeOrderException {
        this.startTime = LocalTime.of(hour, min);
        checkStartTimeEndTimeOrder();
    }

    public void setEndTime(int hour, int min) throws NotExpectedTimeOrderException {
        this.endTime = LocalTime.of(hour, min);
        checkStartTimeEndTimeOrder();
    }

    public void setStartTime(String time) throws NotExpectedTimeOrderException {
        int startHour = Integer.parseInt(time.substring(0, 2));
        int startMinute = Integer.parseInt(time.substring(3));
        this.startTime = LocalTime.of(startHour, startMinute);
        checkStartTimeEndTimeOrder();
    }

    public void setEndTime(String time) throws NotExpectedTimeOrderException {
        int endHour = Integer.parseInt(time.substring(0, 2));
        int endMinute = Integer.parseInt(time.substring(3));
        this.endTime = LocalTime.of(endHour, endMinute);
        checkStartTimeEndTimeOrder();
    }

    public void checkStartTimeEndTimeOrder() throws NotExpectedTimeOrderException {
        if (this.endTime != LocalTime.of(0, 0)) {
            if (this.startTime.isAfter(this.endTime)) {
                throw new NotExpectedTimeOrderException("Not expected time order");
            } else {

            }
        }
    }
}

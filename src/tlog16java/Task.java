/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlog16java;

import java.time.LocalTime;

/**
 *
 * @author Szandi
 */
public class Task {

    String taskId;
    LocalTime startTime;
    LocalTime endTime;
    String comment;

    public Task(String taskId, String comment, int startHour, int startMin, int endHour, int endMin) {
        this.taskId = taskId;
        this.comment = comment;

        startTime = LocalTime.of(startHour, startMin);
        endTime = LocalTime.of(endHour, endMin);
    }

    public Task(String taskId, String comment, String startTime, String endTime) {
        this.taskId = taskId;
        this.comment = comment;

        int startHour = Integer.parseInt(startTime.substring(0, 2));
        int startMinute = Integer.parseInt(startTime.substring(3));
        this.startTime = LocalTime.of(startHour, startMinute);

        int endHour = Integer.parseInt(endTime.substring(0, 2));
        int endMinute = Integer.parseInt(endTime.substring(3));
        this.endTime = LocalTime.of(endHour, endMinute);
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
    
    
    //We count digits two times. Shall be modified!
    boolean isValidRedmineTaskId() {
        boolean isValidRedmineTaskId = false;

        int digitCounter = 0;

        for (char c : taskId.toCharArray()) {
            if (c >= '0' && c <= '9') {
                ++digitCounter;
            }
        }

        if (digitCounter == 4) {
            if (taskId.length() == 4) {
                isValidRedmineTaskId = true;
            }
        }

        return isValidRedmineTaskId;
    }

    boolean isValidLTTaskId() {
        boolean isValidLTTaskId = false;

        int digitCounter = 0;

        for (char c : taskId.toCharArray()) {
            if (c >= '0' && c <= '9') {
                ++digitCounter;
            }
        }

        if (digitCounter == 4) {
            if (taskId.startsWith("LT-") && taskId.length() == 7) {
                isValidLTTaskId = true;
            }
        }

        return isValidLTTaskId;
    }
    //End of 'Shall be modified!' part!

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

    public void setStartTime(int hour, int min) {
        this.startTime = LocalTime.of(hour, min);
    }

    public void setEndTime(int hour, int min) {
        this.endTime = LocalTime.of(hour, min);
    }

    public void setStartTime(String time) {
        int startHour = Integer.parseInt(time.substring(0, 2));
        int startMinute = Integer.parseInt(time.substring(3));
        this.startTime = LocalTime.of(startHour, startMinute);
    }

    public void setEndTime(String time) {
        int endHour = Integer.parseInt(time.substring(0, 2));
        int endMinute = Integer.parseInt(time.substring(3));
        this.endTime = LocalTime.of(endHour, endMinute);
    }
}

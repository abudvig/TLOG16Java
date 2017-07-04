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

    boolean isValidTaskId() {
        int digitCounter = 0;
        boolean isValidTaskIdValue = false;

        for (char c : taskId.toCharArray()) {
            if (c >= '0' && c <= '9') {
                ++digitCounter;
            }
        }

        if (digitCounter == 4) {
            if ((taskId.length() == 4) || (taskId.startsWith("LT-") && taskId.length() == 7)) {
                isValidTaskIdValue = true;
            }
        }

        return isValidTaskIdValue;
    }

    boolean isMultipleQuarterHour() {
        boolean isMultipleQuarterHourValue = false;
        long time = this.getMinPerTask();

        if (time % 15 == 0) {
            isMultipleQuarterHourValue = true;
        }

        return isMultipleQuarterHourValue;
    }
}

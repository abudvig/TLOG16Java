/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlog16java;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author Szandi
 */
public class Util {

    public static boolean isMultipleQuarterHour(long timeInMinutes) {
        boolean isMultipleQuarterHour = false;

        if (timeInMinutes % 15 == 0) {
            isMultipleQuarterHour = true;
        }

        return isMultipleQuarterHour;
    }

    public static long roundToMultipleQuarterHour(LocalTime startTime, LocalTime endTime) {
        long roundedMinutes = 0;
        
        //Next 3 lines copied from Task class's getMinPerTask method. Shall be modified!
        long startTimeInMinutes = (startTime.getHour()) * 60 + startTime.getMinute();
        long endTimeInMinutes = (endTime.getHour()) * 60 + endTime.getMinute();
        long MinPerTask = endTimeInMinutes - startTimeInMinutes;
        //End of 'Shall be modified!' part!

        if (isMultipleQuarterHour(MinPerTask) == false) {
            roundedMinutes = 15 * (Math.round(MinPerTask / 15));
        }

        return roundedMinutes;
    }
    
    public static boolean isSeparatedTime(Task taskToBeChecked, List<Task> tasks) {
        boolean hasCommonTime = false;

        for (int i = 0; i <= tasks.size(); i++) {
            if ((tasks.get(i).endTime.compareTo(taskToBeChecked.startTime) < 0) || (tasks.get(i).startTime.compareTo(taskToBeChecked.endTime) > 0)) {
                //they are separated
            } else if (taskToBeChecked != tasks.get(i)) {
                hasCommonTime = true;
            }
        }

        return !hasCommonTime;
    }
    
    public static boolean isWeekday(LocalDate actualDay) {
        boolean isWeekday = false;

        if ((actualDay.getDayOfWeek() != DayOfWeek.SATURDAY) && (actualDay.getDayOfWeek() != DayOfWeek.SUNDAY)) {
            isWeekday = true;
        }

        return isWeekday;
    }
}

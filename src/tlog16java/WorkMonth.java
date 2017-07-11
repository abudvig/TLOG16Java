/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlog16java;

import java.time.YearMonth;
import java.util.List;

/**
 *
 * @author Szandi
 */
public class WorkMonth {

    List<WorkDay> days;
    YearMonth date;
    long sumPerMonth;
    long requiredMinPerMonth;

    public WorkMonth(List<WorkDay> days, int year, int month, long sumPerMonth, long requiredMinPerMonth) {
        this.days = days;
        date = YearMonth.of(year, month);
        this.sumPerMonth = sumPerMonth;
        this.requiredMinPerMonth = requiredMinPerMonth;
    }

    public WorkMonth(int year, int month) {
        date = YearMonth.of(year, month);
    }

    public List<WorkDay> getDays() {
        return days;
    }

    public YearMonth getDate() {
        return date;
    }

    public long getSumPerMonth() {
        sumPerMonth = 0;
        
        for (int i = 0; i < days.size(); i ++){
            sumPerMonth += days.get(i).getSumPerDay();
        }
        
        return sumPerMonth;
    }

    public long getRequiredMinPerMonth() {
        requiredMinPerMonth = 0;
        
        for (int i = 0; i < days.size(); i ++){
            requiredMinPerMonth += days.get(i).getRequiredMinPerDay();
        }
        
        return requiredMinPerMonth;
    }

    long getExtraMinPerMonth() {
        long sumExtraMins = 0;

        for (int i = 0; i < days.size(); i++) {
            sumExtraMins = sumExtraMins + this.days.get(i).getExtraMinPerDay();
        }

        return sumExtraMins;
    }

    boolean isNewDate(WorkDay dateToCheck) {
        boolean isNewDate = true;

        for (int i = 0; i < days.size(); i++) {
            if (days.get(i).actualDay.equals(dateToCheck.actualDay)) {
                isNewDate = false;
            }

        }

        return isNewDate;
    }

    boolean isSameMonth(WorkDay dateToCheck) {
        boolean isSameMonth = false;

        if (dateToCheck.actualDay.getMonth() == this.date.getMonth()) {
            isSameMonth = true;
        }

        return isSameMonth;
    }

    void addWorkDay(WorkDay workdayToCheck, boolean isWeekendEnabled) {
        if (isSameMonth(workdayToCheck) && isNewDate(workdayToCheck)) {
            if (isWeekendEnabled) {
                days.add(workdayToCheck);
            } else if (isWeekendEnabled == false && Util.isWeekday(workdayToCheck.actualDay)) {
                days.add(workdayToCheck);
            }
        }

    }

    void addWorkDay(WorkDay workdayToCheck) {
        if (isSameMonth(workdayToCheck) && isNewDate(workdayToCheck)) {
            if (Util.isWeekday(workdayToCheck.actualDay)) {
                days.add(workdayToCheck);
            }
        }

    }
}

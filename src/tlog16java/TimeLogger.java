/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlog16java;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Szandi
 */
public class TimeLogger {

    public static List<WorkMonth> months = new ArrayList<>();

    public List<WorkMonth> getMonths() {
        return months;
    }

    public static boolean isNewMonth(WorkMonth monthToCheck) {
        boolean isNewMonth = true;

        for (int i = 0; i < months.size(); i++) {
            if (months.get(i).date.equals(monthToCheck.date)) {
                isNewMonth = false;
            }
            
        }

        return isNewMonth;
    }

    void addMonth(WorkMonth monthToAdd) {
        if (isNewMonth(monthToAdd)) {
            months.add(monthToAdd);
        }
    }
}

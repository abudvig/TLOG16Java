/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlog16java;

import java.util.List;

/**
 *
 * @author Szandi
 */
public class TimeLogger {
    List <WorkMonth> months;

    public List<WorkMonth> getMonths() {
        return months;
    }
    
    boolean isNewMonth(WorkMonth monthToCheck){
        boolean isNewMonth = false;

        if (months.contains(monthToCheck) == false) {
            isNewMonth = true;
        }

        return isNewMonth;
    }
    
    void addMonth(WorkMonth monthToAdd){
        if(isNewMonth(monthToAdd)){
            months.add(monthToAdd);
        }
    }
}

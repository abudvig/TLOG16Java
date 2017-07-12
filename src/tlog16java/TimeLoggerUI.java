/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlog16java;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import timelogger.exceptions.NotExpectedTimeOrderException;

/**
 *
 * @author Szandi
 */
public class TimeLoggerUI {

    //Menu handling starts here
    static List<String> menuItems = Arrays.asList(
            "Exit",
            "List months",
            "List days",
            "List tasks for a specific day",
            "Add new month",
            "Add day to a specific month",
            "Start a task for a day",
            "Finish a specific task",
            "Delete a task",
            "Modify a task",
            "Statistics");

    public static void printMainMenu() {
        System.out.println("Please choose from the following options and write its number:" + "\n");

        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println(i + ". " + menuItems.get(i));
        }
    }

    public static String readResponse() {
        String userResponse;
        Scanner reader = new Scanner(System.in);
        userResponse = reader.nextLine();

//        if(userResponse.isEmpty()){
//            writeInvalid();
//            readResponse();
//        }
        return userResponse;
    }

    public static boolean isResponseANumber(String responseToCheck) {
        boolean isResponseANumber = false;

        if (Util.digitCounter(responseToCheck) == responseToCheck.length()) {
            isResponseANumber = true;
        }

        return isResponseANumber;
    }

    public static int parseStringResponse(String selectionInString) {
        return Integer.parseInt(selectionInString);
    }

    public static boolean isThisAValidMenuPoint(int response) {
        boolean isItValid = false;

        if (response >= 0 && response < menuItems.size()) {
            isItValid = true;
        }

        return isItValid;
    }

    public static void writeInvalid(String whatIsInvalid) {
        System.out.println("Invalid " + whatIsInvalid + "!");
    }

    public static void menuResponseHandler(String responseToCheck) {
        if (!isResponseANumber(responseToCheck) || !isThisAValidMenuPoint(parseStringResponse(responseToCheck))) {
            writeInvalid("menu point");
            menuResponseHandler(readResponse());
        } else { //to be implemented correctly later
            System.out.println("You selected " + parseStringResponse(responseToCheck));
            whichMenuPointToRun(parseStringResponse(responseToCheck));
        }
    }

    public static void doMainMenu() {
        printMainMenu();
        menuResponseHandler(readResponse());
    }

    //Menu handling ends here
    //Menu tasks start here
    public static void whichMenuPointToRun(int menuPointNumber) {
        //to be implemented later
        try {
            switch (menuPointNumber) {
                case 0:
                    doExit();
                    break;
                case 1:
                    doListMonths();
                    break;
                case 2:
                    doListDays();
                    break;
                case 3:
                    doListTasks();
                    break;
                case 4:
                    doAddNewMonth();
                    break;
                case 5:
                    doAddNewDay();
                    break;
                case 6:
                    doStartTask();
                    break;
                case 7:
                    doFinishTask();
                    break;
                case 8:
                    doDeleteTask();
                    break;
                case 9:
                    doModifyTask();
                    break;
                case 10:
                    doStatistics();
                    break;
            }
        } catch (NotExpectedTimeOrderException e) {
            System.out.println(e);
        }

        doMainMenu();
    }

    //------------Task 0------------
    public static void doExit() {
        //to be checked
        System.exit(0);
    }

    //------------Task 1------------
    public static void doListMonths() {
        for (int i = 0; i < TimeLogger.months.size(); i++) {
            System.out.println(i + 1 + ". " + TimeLogger.months.get(i).date);
        }
    }

    //------------Task 2------------ 
    public static void listDays(String monthIndex) {
        if (isItAValidMonthIndex(monthIndex)) {
            int monthIndexInt = parseStringResponse(monthIndex) - 1;
            for (int i = 0; i < TimeLogger.months.get(monthIndexInt).days.size(); i++) {
                System.out.println(i + 1 + ". " + TimeLogger.months.get(monthIndexInt).days.get(i).actualDay);
            }
        }
    }

    public static void doListDays() {
        doListMonths();
        String monthIndex = askForMonth();
        listDays(monthIndex);
    }

    //------------Task 3------------
    public static void listTasks(String monthIndex, String dayIndex) {
        int monthIndexInt = parseStringResponse(monthIndex) - 1;

        if (isItAValidDayIndex(monthIndexInt, dayIndex)) {
            int dayIndexInt = parseStringResponse(dayIndex) - 1;
            for (int i = 0; i < TimeLogger.months.get(monthIndexInt).days.get(dayIndexInt).tasks.size(); i++) {
                System.out.println(
                        i + 1 + ". "
                        + TimeLogger.months.get(monthIndexInt).days.get(dayIndexInt).tasks.get(i).taskId
                        + ": "
                        + TimeLogger.months.get(monthIndexInt).days.get(dayIndexInt).tasks.get(i).comment
                        + " "
                        + TimeLogger.months.get(monthIndexInt).days.get(dayIndexInt).tasks.get(i).startTime
                        + "-"
                        + TimeLogger.months.get(monthIndexInt).days.get(dayIndexInt).tasks.get(i).endTime);
            }
        }
    }

    public static void doListTasks() {
        doListMonths();
        String monthIndex = askForMonth();
        listDays(monthIndex);
        String dayIndex = askForDay();
        listTasks(monthIndex, dayIndex);
    }

    //------------Task 4------------
    public static List<String> readNewMonth() {
        List<String> newDate = new ArrayList<>();

        System.out.println("Year: ");
        newDate.add(readResponse());

        System.out.println("Month: ");
        newDate.add(readResponse());

        return newDate;
    }

    public static boolean isItAValidNewMonth(String year, String month) {
        boolean isItValid = false;

        if (isResponseANumber(year) && isResponseANumber(month)) {
            int yearInt = parseStringResponse(year);
            int monthInt = parseStringResponse(month);
            if (monthInt > 0
                    && monthInt <= 12
                    && Util.digitCounter(year) == 4
                    && TimeLogger.isNewMonth(new WorkMonth(yearInt, monthInt))) {
                isItValid = true;
            }
        }

        return isItValid;
    }

    public static void addNewMonth(int year, int month) {
        List<WorkDay> days = new ArrayList<>();
        TimeLogger.months.add(new WorkMonth(days, year, month, 0, 0));
    }

    public static void doAddNewMonth() {
        List<String> date = readNewMonth();

        if (isItAValidNewMonth(date.get(0), date.get(1))) {
            addNewMonth(parseStringResponse(date.get(0)), parseStringResponse(date.get(1)));
            System.out.println("Month succesfully added");
        } else {
            writeInvalid("new month");
            doAddNewMonth();
        }
    }

    //------------Task 5------------
    public static String askForMonth() {
        System.out.println("Write the month index: ");
        String month = readResponse();
        return month;
    }

    public static boolean isItAValidMonthIndex(String monthIndex) {
        boolean isItValid = false;

        if (isResponseANumber(monthIndex)) {
            int monthIndexInt = parseStringResponse(monthIndex);
            if (monthIndexInt - 1 < TimeLogger.months.size() && monthIndexInt >= 0) {
                isItValid = true;
            }
        }

        return isItValid;
    }

    public static String askForDay() {
        System.out.println("Write the day index: ");
        String dayString = readResponse();

        return dayString;
    }

    public static boolean isItAValidDay(String dayToCheck) { //meg kell nézni, benne van-e már a tömbben
        boolean isItValid = false;

        if (isResponseANumber(dayToCheck)) {
            int dayToCheckInt = parseStringResponse(dayToCheck);
            if (dayToCheckInt > 0 && dayToCheckInt < 32) {
                isItValid = true;
            }
        }

        return isItValid;
    }

    public static String askForWorkingHours() {
        System.out.println("Write the required working hours: ");
        String hours = readResponse();
        return hours;
    }

    public static boolean isItAValidHour(String hourToCheck) {
        boolean isItValid = false;

        if (isResponseANumber(hourToCheck)) {
            int hourToCheckInt = parseStringResponse(hourToCheck);
            if (hourToCheckInt < 24 && hourToCheckInt >= 0) {
                isItValid = true;
            }
        }

        return isItValid;
    }

    public static void addNewDay(int monthIndex, WorkDay day) {
        TimeLogger.months.get(monthIndex).addWorkDay(day);
    }

    public static void doAddNewDay() {
        doListMonths();
        String monthIndex = askForMonth();
        String day = askForDay();
        String workingHours = askForWorkingHours();

        try {
            if (isItAValidMonthIndex(monthIndex) && isItAValidDay(day) && isItAValidHour(workingHours)) {
                System.out.println("success");

                int monthIndexInt = parseStringResponse(monthIndex) - 1;
                long workHoursLong = parseStringResponse(workingHours) * 60;
                int dayInt = parseStringResponse(day);

                List<Task> tasks = new ArrayList<>();
                WorkDay newDay = new WorkDay(
                        tasks,
                        workHoursLong,
                        TimeLogger.months.get(monthIndexInt).date.getYear(),
                        TimeLogger.months.get(monthIndexInt).date.getMonthValue(),
                        dayInt,
                        0);
                addNewDay(monthIndexInt, newDay);
            } else {
                writeInvalid("input");
                doAddNewDay();
            }
        } catch (java.time.DateTimeException e) {
            writeInvalid("input");
            doAddNewDay();
        }

    }

    //------------Task 6------------
    public static boolean isItAValidDayIndex(int monthIndex, String dayIndex) {
        boolean isItValid = false;

        if (isResponseANumber(dayIndex)) {
            int dayIndexInt = parseStringResponse(dayIndex);
            if (dayIndexInt - 1 < TimeLogger.months.get(monthIndex).days.size() && dayIndexInt >= 0) {
                isItValid = true;
            }
        }

        return isItValid;
    }

    public static String askForTaskId() {
        System.out.println("Write the taskId: ");
        String taskId = readResponse();
        return taskId;
    }

    public static boolean isItANewTaskId(int monthIndex, int dayIndex, String taskId) {
        boolean isItNew = true;

        for (int i = 0; i < TimeLogger.months.get(monthIndex).days.get(dayIndex).tasks.size(); i++) {
            if (TimeLogger.months.get(monthIndex).days.get(dayIndex).tasks.get(i).taskId.equals(taskId)) {
                isItNew = false;
            }
        }

        return isItNew;
    }

    public static String askForComment() {
        System.out.println("Write the comment: ");
        String comment = readResponse();
        return comment;
    }

    public static String getLatestEndTime(int monthIndex, int dayIndex) {
        int lastIndex = TimeLogger.months.get(monthIndex).days.get(dayIndex).tasks.size() - 1;

        if (TimeLogger.months.get(monthIndex).days.get(dayIndex).tasks.size() > 0) {
            return TimeLogger.months.get(monthIndex).days.get(dayIndex).tasks.get(lastIndex).endTime.toString();
        } else {
            return "00:00";
        }

    }

    public static String askForStartTime(String endTime) {
        System.out.println("Press enter to use the earlier end time as start time!");
        System.out.println("Or write the start time in HH:mm format: ");
        System.out.println("Latest end time [" + endTime + "]");
        String time = readResponse();
        return time;
    }

    public static boolean isItAValidTime(String timeToCheck) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(timeToCheck.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public static void addTask(int monthIndex, int dayIndex, Task task) {
        if (task.isValidTaskId() && isItANewTaskId(monthIndex, dayIndex, task.taskId)) {
            TimeLogger.months.get(monthIndex).days.get(dayIndex).tasks.add(task);
        }
        System.out.println("success");
    }

    public static void doStartTask() throws NotExpectedTimeOrderException {
        doListMonths();
        String monthIndex = askForMonth();

        if (isItAValidMonthIndex(monthIndex)) {
            listDays(monthIndex);
            String day = askForDay();
            int monthIndexInt = parseStringResponse(monthIndex) - 1;
            if (isItAValidDayIndex(monthIndexInt, day)) {
                String taskId = askForTaskId();
                int dayIndexInt = parseStringResponse(day) - 1;
                if (isItANewTaskId(monthIndexInt, dayIndexInt, taskId)) {
                    String comment = askForComment();
                    String startTime = askForStartTime(getLatestEndTime(monthIndexInt, dayIndexInt));
                    if (startTime.isEmpty()) {
                        startTime = getLatestEndTime(monthIndexInt, dayIndexInt);
                        Task newTask = new Task(taskId, comment, startTime, "00:00");
                        addTask(monthIndexInt, dayIndexInt, newTask);
                    } else if (isItAValidTime(startTime)) {
                        Task newTask = new Task(taskId, comment, startTime, "00:00");
                        addTask(monthIndexInt, dayIndexInt, newTask);
                    } else {
                        writeInvalid("start time");
                        doStartTask();
                    }
                } else {
                    System.out.println("Already existing task");
                    doStartTask();
                }
            } else {
                writeInvalid("day index");
                doStartTask();
            }
        } else {
            writeInvalid("month index");
            doStartTask();
        }
    }

    //------------Task 7------------
    public static void listUnfinishedTasks(int monthIndex, int dayIndex) {
        for (int i = 0; i < TimeLogger.months.get(monthIndex).days.get(dayIndex).tasks.size(); i++) {
            if (TimeLogger.months.get(monthIndex).days.get(dayIndex).tasks.get(i).endTime == LocalTime.of(0, 0)) {
                System.out.println(
                        i + 1 + ". "
                        + TimeLogger.months.get(monthIndex).days.get(dayIndex).tasks.get(i).taskId
                        + ": "
                        + TimeLogger.months.get(monthIndex).days.get(dayIndex).tasks.get(i).comment
                        + " "
                        + TimeLogger.months.get(monthIndex).days.get(dayIndex).tasks.get(i).startTime
                        + "-"
                        + TimeLogger.months.get(monthIndex).days.get(dayIndex).tasks.get(i).endTime);
            }
        }
    }

    public static String askForTaskIndex() {
        System.out.println("Write the task index: ");
        String taskIndex = readResponse();
        return taskIndex;
    }

    public static boolean isItAValidTaskIndex(int monthIndex, int dayIndex, String taskIndex) {
        boolean isItValid = false;

        if (isResponseANumber(taskIndex)) {
            int taskIndexInt = parseStringResponse(taskIndex);
            if (taskIndexInt - 1 < TimeLogger.months.get(monthIndex).days.get(dayIndex).tasks.size() && taskIndexInt >= 0) {
                isItValid = true;
            }
        }

        return isItValid;
    }

    public static String askForEndTime() {
        System.out.println("Write the end time in HH:mm format: ");
        String endTime = readResponse();
        return endTime;
    }

    public static void addEndTime(int monthIndex, int dayIndex, int taskIndex, String endTime) throws NotExpectedTimeOrderException {
        TimeLogger.months.get(monthIndex).days.get(dayIndex).tasks.get(taskIndex).setEndTime(endTime);
    }

    public static void doFinishTask() throws NotExpectedTimeOrderException {
        doListMonths();
        String monthIndex = askForMonth();

        if (isItAValidMonthIndex(monthIndex)) {
            listDays(monthIndex);
            String day = askForDay();
            int monthIndexInt = parseStringResponse(monthIndex) - 1;
            if (isItAValidDayIndex(monthIndexInt, day)) {
                int dayIndexInt = parseStringResponse(day) - 1;
                listUnfinishedTasks(monthIndexInt, dayIndexInt);
                String taskIndex = askForTaskIndex();
                if (isItAValidTaskIndex(monthIndexInt, dayIndexInt, taskIndex)) {
                    int taskIndexInt = parseStringResponse(taskIndex) - 1;
                    String endTime = askForEndTime();
                    if (isItAValidTime(endTime)) {
                        addEndTime(monthIndexInt, dayIndexInt, taskIndexInt, endTime);
                        System.out.println("Success");
                    } else {
                        writeInvalid("end time");
                        doFinishTask();
                    }
                } else {
                    writeInvalid("task index");
                    doFinishTask();
                }
            } else {
                writeInvalid("day index");
                doFinishTask();
            }
        } else {
            writeInvalid("month index");
            doFinishTask();
        }
    }

    //------------Task 8------------
    public static String confirmation() {
        System.out.println("Are you sure you want to delete this task? (yes/anything else)");
        return readResponse();
    }

    public static boolean whatToDo(String answer) {
        if (answer.equals("yes")) {
            return true;
        } else {
            return false;
        }
    }

    public static void deleteTask(int monthIndex, int dayIndex, int taskIndex) {
        TimeLogger.months.get(monthIndex).days.get(dayIndex).tasks.remove(taskIndex);
    }

    public static void doDeleteTask() {
        doListMonths();
        String monthIndex = askForMonth();

        if (isItAValidMonthIndex(monthIndex)) {
            listDays(monthIndex);
            String day = askForDay();
            int monthIndexInt = parseStringResponse(monthIndex) - 1;
            if (isItAValidDayIndex(monthIndexInt, day)) {
                int dayIndexInt = parseStringResponse(day) - 1;
                listTasks(monthIndex, day);
                String taskIndex = askForTaskIndex();
                if (isItAValidTaskIndex(monthIndexInt, dayIndexInt, taskIndex)) {
                    int taskIndexInt = parseStringResponse(taskIndex) - 1;
                    String confirmation = confirmation();
                    if (whatToDo(confirmation)) {
                        deleteTask(monthIndexInt, dayIndexInt, taskIndexInt);
                        System.out.println("Success");
                    } else {
                        writeInvalid("input");
                        doDeleteTask();
                    }
                } else {
                    writeInvalid("task index");
                    doDeleteTask();
                }
            } else {
                writeInvalid("day index");
                doDeleteTask();
            }
        } else {
            writeInvalid("month index");
            doDeleteTask();
        }
    }

    //------------Task 9------------
    public static void modifyTaskId(int monthIndex, int dayIndex, int taskIndex, String taskId) throws NotExpectedTimeOrderException {
        if (taskId.isEmpty()) {
            System.out.println("Not modified");
        } else {
            Task newTask = new Task(taskId, "", "00:00", "00:00");
            System.out.println("success");
            if (newTask.isValidTaskId()) {
                TimeLogger.months.get(monthIndex).days.get(dayIndex).tasks.get(taskIndex).setTaskId(taskId);
                System.out.println("success");
            } else {
                writeInvalid("task id");
                doModifyTask();
            }
        }
    }

    public static void modifyComment(int monthIndex, int dayIndex, int taskIndex, String comment) {
        if (comment.isEmpty()) {
            System.out.println("Not modified");
        } else {
            TimeLogger.months.get(monthIndex).days.get(dayIndex).tasks.get(taskIndex).setComment(comment);
        }
    }

    public static String askForStartTime() {
        System.out.println("Write the start time in HH:mm format: ");
        String time = readResponse();
        return time;
    }

    public static void modifyStartTime(int monthIndex, int dayIndex, int taskIndex, String startTime) throws NotExpectedTimeOrderException {
        if (startTime.isEmpty()) {
            System.out.println("Not modified");
        } else if (isItAValidTime(startTime)) {
            TimeLogger.months.get(monthIndex).days.get(dayIndex).tasks.get(taskIndex).setStartTime(startTime);
        } else {
            writeInvalid("start time");
            doModifyTask();
        }
    }

    public static void modifyEndTime(int monthIndex, int dayIndex, int taskIndex, String endTime) throws NotExpectedTimeOrderException {
        if (endTime.isEmpty()) {
            System.out.println("Not modified");
        } else if (isItAValidTime(endTime)) {
            TimeLogger.months.get(monthIndex).days.get(dayIndex).tasks.get(taskIndex).setEndTime(endTime);
        } else {
            writeInvalid("end time");
            doModifyTask();
        }
    }

    public static void modification(int monthIndex, int dayIndex, int taskIndex) throws NotExpectedTimeOrderException {
        System.out.println("Previous task id: [" + TimeLogger.months.get(monthIndex).days.get(dayIndex).tasks.get(taskIndex).taskId + "]");
        String newTaskId = askForTaskId();
        modifyTaskId(monthIndex, dayIndex, taskIndex, newTaskId);

        System.out.println("Previous comment: [" + TimeLogger.months.get(monthIndex).days.get(dayIndex).tasks.get(taskIndex).comment + "]");
        String newComment = askForComment();
        modifyComment(monthIndex, dayIndex, taskIndex, newComment);

        System.out.println("Previous start time: [" + TimeLogger.months.get(monthIndex).days.get(dayIndex).tasks.get(taskIndex).startTime + "]");
        String newStartTime = askForStartTime();
        modifyStartTime(monthIndex, dayIndex, taskIndex, newStartTime);

        System.out.println("Previous end time: [" + TimeLogger.months.get(monthIndex).days.get(dayIndex).tasks.get(taskIndex).endTime + "]");
        String newEndTime = askForEndTime();
        modifyEndTime(monthIndex, dayIndex, taskIndex, newEndTime);
    }

    public static void doModifyTask() throws NotExpectedTimeOrderException {
        doListMonths();
        String monthIndex = askForMonth();

        if (isItAValidMonthIndex(monthIndex)) {
            listDays(monthIndex);
            String day = askForDay();
            int monthIndexInt = parseStringResponse(monthIndex) - 1;
            if (isItAValidDayIndex(monthIndexInt, day)) {
                int dayIndexInt = parseStringResponse(day) - 1;
                listTasks(monthIndex, day);
                String taskIndex = askForTaskIndex();
                if (isItAValidTaskIndex(monthIndexInt, dayIndexInt, taskIndex)) {
                    int taskIndexInt = parseStringResponse(taskIndex) - 1;
                    modification(monthIndexInt, dayIndexInt, taskIndexInt);
                } else {
                    writeInvalid("task index");
                    doModifyTask();
                }
            } else {
                writeInvalid("day index");
                doModifyTask();
            }
        } else {
            writeInvalid("month index");
            doModifyTask();
        }
    }

    //------------Task 10------------
    public static void printMonthStatistics(int monthIndex) {
        System.out.println(TimeLogger.months.get(monthIndex).date.getYear() + " " + TimeLogger.months.get(monthIndex).date.getMonth());
        System.out.println("====================");
        System.out.println("Required minutes " + TimeLogger.months.get(monthIndex).getRequiredMinPerMonth());
        System.out.println("Minutes done: " + TimeLogger.months.get(monthIndex).getSumPerMonth());
        System.out.println("Extra minutes: " + TimeLogger.months.get(monthIndex).getExtraMinPerMonth());
    }

    public static void printDayStatistics(int monthIndex) {
        for (int i = 0; i < TimeLogger.months.get(monthIndex).days.size(); i++) {
            System.out.println(TimeLogger.months.get(monthIndex).days.get(i).actualDay);
            System.out.println("--------------------");
            System.out.println("Required minutes: " + TimeLogger.months.get(monthIndex).days.get(i).requiredMinPerDay);
            System.out.println("Minutes done: " + TimeLogger.months.get(monthIndex).days.get(i).getSumPerDay());
            System.out.println("Extra minutes: " + TimeLogger.months.get(monthIndex).days.get(i).getExtraMinPerDay());
            System.out.println("");
        }
    }

    public static void doStatistics() {
        doListMonths();
        String monthIndex = askForMonth();

        if (isItAValidMonthIndex(monthIndex)) {
            int monthIndexInt = parseStringResponse(monthIndex) - 1;
            printMonthStatistics(monthIndexInt);
            System.out.println("");
            printDayStatistics(monthIndexInt);
        }
    }

    //Menu tasks end here
}

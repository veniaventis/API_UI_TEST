package framework.utils;

import framework.logger.LoggerUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataUtils {


    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy h:mm a");


    public static String getCurrentDate() {
        LoggerUtils.info("get current date");
        return LocalDateTime.now().format(dateFormatter);
    }

    public static String getCurrentDateAndTime() {
        LoggerUtils.info("get current date and time");
        return LocalDateTime.now().format(dateTimeFormatter).toLowerCase();
    }

    public static String getNextDate(int month, int day) {
        LoggerUtils.info("get next " + day + "/" + month);
        LocalDate date = LocalDate.now();
        if (month == 2 && day == 29) {
            if (date.isLeapYear()) {
                if (date.isBefore(LocalDate.of(date.getYear(), month, day))) {
                    return LocalDate.of(date.getYear(), month, day).format(dateFormatter);
                } else {
                    return LocalDate.of(date.getYear() + 4, month, day).format(dateFormatter);
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    date = date.plusYears(1);
                    if (date.isLeapYear()) {
                        return LocalDate.of(date.getYear(), month, day).format(dateFormatter);
                    }
                }
            }
            LoggerUtils.error("date hasn't been found");
            return "";
        } else {
            if (date.isBefore(LocalDate.of(date.getYear(), month, day))) {
                return LocalDate.of(date.getYear(), month, day).format(dateFormatter);
            } else {
                return LocalDate.of(date.getYear() + 1, month, day).format(dateFormatter);
            }
        }
    }
}

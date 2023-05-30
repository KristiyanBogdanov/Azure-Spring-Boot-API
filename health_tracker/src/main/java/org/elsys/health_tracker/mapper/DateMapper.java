package org.elsys.health_tracker.mapper;

import java.time.LocalDate;

public class DateMapper {
    public static String toString(LocalDate date) {
        return date.toString();
    }

    public static LocalDate toSQLDate(String date) {
        return LocalDate.parse(date);
    }
}

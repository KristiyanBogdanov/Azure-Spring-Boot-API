package org.elsys.health_tracker.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateMapper {
    public static String toString(LocalDate date) {
        return date.toString();
    }

    public static LocalDate toSQLDate(String date) {
        return LocalDate.parse(date);
    }

    public static LocalDateTime toLocalDateTime(String date) {
        return LocalDateTime.parse(date);
    }
}

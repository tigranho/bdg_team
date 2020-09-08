package lesson8.time_api;

import java.time.*;

import static java.lang.System.*;
import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        out.println(LocalDate.now());
        out.println(LocalTime.now());
        out.println(LocalDateTime.now());

        LocalDate date1 = LocalDate.of(2015, Month.JANUARY, 20);
        LocalDate date2 = LocalDate.of(2015, 1, 20);

        LocalTime time1 = LocalTime.of(6, 15);
        LocalTime time2 = LocalTime.of(6, 15, 30);
        LocalTime time3 = LocalTime.of(6, 15, 30, 200);

        LocalDateTime dateTime1 = LocalDateTime.of(2015, Month.JANUARY, 20, 6, 15, 30);
        LocalDateTime dateTime2 = LocalDateTime.of(date1, time1);

        LocalDate date = LocalDate.of(2014, Month.JANUARY, 20);
        out.println(date);
        date = date.plusDays(2);
        out.println(date);
        date = date.plusWeeks(1);
        out.println(date);
        date = date.plusMonths(1);
        out.println(date);
        date = date.plusYears(5);
        out.println(date);

        LocalDate date3 = LocalDate.of(2020, Month.JANUARY, 20);
        LocalTime time = LocalTime.of(5, 15);
        LocalDateTime dateTime = LocalDateTime.of(date3, time);
        out.println(dateTime);
        dateTime = dateTime.minusDays(1);
        out.println(dateTime);
        dateTime = dateTime.minusHours(10);
        out.println(dateTime);
        dateTime = dateTime.minusSeconds(30);
        out.println(dateTime);

        LocalDate start = LocalDate.of(2015, Month.JANUARY, 1);
        LocalDate end = LocalDate.of(2015, Month.MARCH, 30);
        performAnimalEnrichment(start, end);
    }

    private static void performAnimalEnrichment(LocalDate start, LocalDate end) {
        LocalDate upTo = start;
        while (upTo.isBefore(end)) {
            System.out.println("give new toy: " + upTo);
            upTo = upTo.plusMonths(1);
        }}
}

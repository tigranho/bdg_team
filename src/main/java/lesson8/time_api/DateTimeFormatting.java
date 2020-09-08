package lesson8.time_api;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import static java.lang.System.out;

public class DateTimeFormatting {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2020, Month.JANUARY, 20);
        out.println(date.getDayOfWeek());
        out.println(date.getMonth());
        out.println(date.getYear());
        out.println(date.getDayOfYear());

        LocalDate date1 = LocalDate.of(2020, Month.JANUARY, 20);
        LocalTime time = LocalTime.of(11, 12, 34);
        LocalDateTime dateTime = LocalDateTime.of(date1, time);
        out.println(date1
                .format(DateTimeFormatter.ISO_LOCAL_DATE));
        out.println(time.format(DateTimeFormatter.ISO_LOCAL_TIME));
        out.println(dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        DateTimeFormatter shortDateTime =
                DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        out.println(shortDateTime.format(dateTime));
        out.println(shortDateTime.format(date));
//        out.println(shortDateTime.format(time));

        LocalDate date2 = LocalDate.of(2020, Month.JANUARY, 20);
        LocalTime time2 = LocalTime.of(11, 12, 34);
        LocalDateTime dateTime2 = LocalDateTime.of(date2, time2);
        DateTimeFormatter shortF = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.SHORT);
        DateTimeFormatter mediumF = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.MEDIUM);
        out.println(shortF.format(dateTime2));
        out.println(mediumF.format(dateTime2));

        DateTimeFormatter f = DateTimeFormatter.ofPattern("MMMM dd, yyyy, hh:mm");
        out.println(dateTime.format(f));

        DateTimeFormatter f1 = DateTimeFormatter.ofPattern("MM dd yyyy");
        LocalDate date3 = LocalDate.parse("01 02 2015", f1);
        LocalTime time4 = LocalTime.parse("11:22");
        out.println(date3);
        out.println(time4);

    }
}

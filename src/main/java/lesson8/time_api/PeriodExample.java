package lesson8.time_api;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

import static java.lang.System.out;

public class PeriodExample {
    public static void main(String[] args) {
        LocalDate start = LocalDate.of(2015, Month.JANUARY, 1);
        LocalDate end = LocalDate.of(2015, Month.MARCH, 30);
        Period period = Period.ofMonths(1);
        performAnimalEnrichment(start, end, period);

        Period annually = Period.ofYears(1);
        Period quarterly = Period.ofMonths(3);
        Period everyThreeWeeks = Period.ofWeeks(3);
        Period everyOtherDay = Period.ofDays(2);
        Period everyYearAndAWeek = Period.of(1, 0, 7);
    }

    private static void performAnimalEnrichment(LocalDate start, LocalDate end,
                                                Period period) {
        LocalDate upTo = start;
        while (upTo.isBefore(end)) {
            out.println("give new toy: " + upTo);
            upTo = upTo.plus(period);
        }
    }
}

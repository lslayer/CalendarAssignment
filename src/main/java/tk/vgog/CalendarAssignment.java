package tk.vgog;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 Simple library to calculate the number of workdays between two given dates.
 */
public class CalendarAssignment {
    public static final List<DayOfWeek> OFF_DAYS = Arrays.asList(DayOfWeek.SUNDAY, DayOfWeek.SATURDAY);
    private final List<LocalDate> holidays = new ArrayList<>();

    //Iteration three - functional style
    public Long calculateWorkdays(LocalDate from, LocalDate to) {
        return from.datesUntil(to, Period.ofDays(1)).toList()
                .stream()
                .filter(Predicate.not(d -> OFF_DAYS.contains(d.getDayOfWeek())))
                .filter(Predicate.not(holidays::contains))
                .count();
    }

    //Thinking about outsourcing this
    public void addHoliday(LocalDate holiday) {
        this.holidays.add(holiday);
    }

    //Package-private for tests
    void clearHolidays() {
        this.holidays.clear();
    }

}

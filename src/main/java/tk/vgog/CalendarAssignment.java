package tk.vgog;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 Simple library to calculate the number of workdays between two given dates.
 */
public class CalendarAssignment {
    public static final Long ZERO_DAYS = 0L;
    private final List<LocalDateTime> holidays = new ArrayList<>();

    //Iteration two - return number of days, minus holidays
    public Long calculateWorkdays(LocalDate from, LocalDate to) {
        if (from.isAfter(to)) {
            return ZERO_DAYS;
        }
        long holidaysQuantity = holidays
                .stream()
                .filter(d -> d.isAfter(from.atStartOfDay()) && d.isBefore(to.atStartOfDay()))
                .count();
        Duration daysBetween = Duration.between(from.atStartOfDay(), to.atStartOfDay());
        return daysBetween.minusDays(holidaysQuantity).toDays();
    }

    public void addHoliday(LocalDate holiday) {
        this.holidays.add(holiday.atStartOfDay());
    }

    void clearHolidays() {
        this.holidays.clear();
    }

}

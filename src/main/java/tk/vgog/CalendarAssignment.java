package tk.vgog;

import java.time.Duration;
import java.time.LocalDate;

/**
 Simple library to calculate the number of workdays between two given dates.
 */
public class CalendarAssignment {
    public static final Long ZERO_DAYS = 0L;

    //Iteration one - return number of days
    public Long calculateWorkdays(LocalDate from, LocalDate to) {
        if (from.isAfter(to)) {
            return ZERO_DAYS;
        }
        Duration daysBetween = Duration.between(from.atStartOfDay(), to.atStartOfDay());
        return daysBetween.toDays();
    }
}

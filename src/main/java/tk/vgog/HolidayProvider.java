package tk.vgog;

import java.time.LocalDate;
import java.util.List;

/**
 * This clasee is for providing holidays, got from different sources, to CalendarAssignment
 */
public interface HolidayProvider {
    void clearHolidays();
    List<LocalDate> getHolidays();

    // I understand, there is one more way to provide holidays,
    // create something like *HolidaysSource* and pass it to CalendarAssignment
    // Anyway, I choose this way, it looks more flexible for me, despite this ugly
    // Object... args
    void fillHolidays(Object... args);
}

package tk.vgog;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.Predicate;


/**
 Simple library to calculate the number of workdays between two given dates.
 */
public class CalendarAssignment {
    /**
     * There can be just offDays here in CalendarAssesment,
     * but sometimes weekend are not those days we used to have
     * It can be easyly skipped, as we have several constructors
     */
    private final CalendarConfiguration configuration;
    private final HolidayProvider holidayProvider;

    public CalendarAssignment(CalendarConfiguration configuration, HolidayProvider holidayProvider) {
        this.configuration = configuration;
        this.holidayProvider = holidayProvider;
    }

    public CalendarAssignment(HolidayProvider holidayProvider) {
        //Default configuration
        this.configuration = new CalendarConfiguration();
        this.holidayProvider = holidayProvider;
    }

    public CalendarAssignment() {
        //Default configuration, and no holidays.
        configuration = new CalendarConfiguration();
        holidayProvider = new EmptyHolidayProvider();
    }

    //Iteration four - correct counting, comments
    public Long calculateWorkdays(LocalDate from, LocalDate to) {
        // datesUntil counts "to" date as exclusive, but I have to do inclusive
        // so, I added one day to "to" date.
        // I can't just increase result by one, because "to" can be off_day or holiday
        return from.datesUntil(to.plusDays(1), Period.ofDays(1)).toList()
                .stream()
                .filter(Predicate.not(d -> configuration.getOffDays().contains(d.getDayOfWeek())))
                .filter(Predicate.not(holidayProvider.getHolidays()::contains))
                .count();
    }
}

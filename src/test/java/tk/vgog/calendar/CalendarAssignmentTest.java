package tk.vgog.calendar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalendarAssignmentTest {

    CalendarAssignment assignment;
    final CalendarConfiguration configuration = new CalendarConfiguration();
    final HolidayProvider holidayProvider = new JSONHolidayProvider();
    private final LocalDate from = LocalDate.of(2022, 8, 1);
    private final LocalDate to = LocalDate.of(2022, 8, 20);

    @Test
    @DisplayName("Days between two dates")
    void givenTwoDates_whenCalculatingDurationBetween_gotActualNumberOfDays() {
        assignment = new CalendarAssignment();
        assertEquals(15L, assignment.calculateWorkdays(from, to));
    }

    @Test
    @DisplayName("Days between two dates respecting holidays")
    void givenTwoDatesAndHolidays_whenCalculatingDurationBetween_gotDaysRespectingHolidays() {
        String externalJson = "{\"holidays\" : [\"20220812\", \"20220811\"]}";
        holidayProvider.fillHolidays(externalJson);
        assignment = new CalendarAssignment(configuration, holidayProvider);
        assertEquals(13L, assignment.calculateWorkdays(from, to));
    }

    @Test
    @DisplayName("Load external holidays")
    void givenJson_whenLoadingExternalHolidays_gotDaysRespectingHolidaysAndWeekends() {
        String holidaysExternal = """
                {
                  "holidays": [
                    "20220701",
                    "20220705"
                  ]
                }
                """;
        holidayProvider.fillHolidays(holidaysExternal);
        assignment = new CalendarAssignment(holidayProvider);
        assertEquals(10L, assignment.calculateWorkdays(
                LocalDate.of(2022, 6, 27),
                LocalDate.of(2022, 7, 12)));
    }

    @Test
    @DisplayName("External requirements")
    void givenPredefinedDates_whenCalculating_gotDaysRespectingHolidaysAndWorkDays() {
        holidayProvider.fillHolidays("{'holidays':['20220701']}");
        assignment = new CalendarAssignment(holidayProvider);
        assertEquals(5L, assignment.calculateWorkdays(
                LocalDate.of(2022, 6, 27),
                LocalDate.of(2022, 7, 4)));
    }

    @Test
    @DisplayName("Custom Weekend (Sun, Mon, Tue")
    void givenCustomWeekend_whenCalculating_gotDaysRespectingHolidaysAndWorkDays() {
        CalendarConfiguration calendarConfiguration = new CalendarConfiguration(Arrays.asList(DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY));
        assignment = new CalendarAssignment(calendarConfiguration, new EmptyHolidayProvider());
        assertEquals(4L, assignment.calculateWorkdays(
                LocalDate.of(2022, 6, 27),
                LocalDate.of(2022, 7, 4)));
    }

}
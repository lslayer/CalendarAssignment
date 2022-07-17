package tk.vgog;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CalendarAssignmentTest {

    CalendarAssignment assignment = new CalendarAssignment();
    private final LocalDate from = LocalDate.of(2022, 8, 1);
    private final LocalDate to = LocalDate.of(2022, 8, 20);

    @BeforeEach
    void setUp() {

        //Nothing for now

    }

    @AfterEach
    void tearDown() {
        assignment.clearHolidays();
    }

    @Test
    @DisplayName("Days between two dates")
    void givenTwoDates_whenCalculatingDurationBetween_gotActualNumberOfDays() {
        assertEquals(assignment.calculateWorkdays(from, to), 19L);
    }

    @Test
    @DisplayName("Days between two dates respecting holidays")
    void givenTwoDatesAndHolidays_whenCalculatingDurationBetween_gotDaysRespectingHolidays() {
        assignment.addHoliday(LocalDate.of(2022, 8, 12));
        assertEquals(assignment.calculateWorkdays(from, to), 18);
    }
}
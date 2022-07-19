package tk.vgog.calendar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class JSONHolidayProviderTest {
    private final String holidaysExternal = """
            {
              "holidays": [
                "20220701",
                "20220705"
              ]
            }
            """;

    private final HolidayProvider provider = new JSONHolidayProvider();
    @Test
    @DisplayName("Correct json parsing")
    void givenJSONData_whenFillHolidays_gotHolidaysContainsAllData() {
        provider.fillHolidays(holidaysExternal);
        assertEquals(2, provider.getHolidays().size());
        assertTrue(provider.getHolidays().contains(LocalDate.of(2022,7,1)));
        assertTrue(provider.getHolidays().contains(LocalDate.of(2022,7,5)));
        assertFalse(provider.getHolidays().contains(LocalDate.of(2022,7,3)));
    }

    @Test
    @DisplayName("Clear contents")
    void givenJSONData_whenClearHolidays_gotHolidaysEmpty() {
        provider.fillHolidays(holidaysExternal);
        assertTrue(provider.getHolidays().size() > 0);
        provider.clearHolidays();
        assertEquals(0, provider.getHolidays().size());
    }

    @Test
    @DisplayName("Incorrect argument passed")
    void givenIncorrectData_whenFillHolidays_gotException() {
        InvalidParameterException thrown = Assertions.assertThrows(InvalidParameterException.class,
                () -> provider.fillHolidays(123L));
        assertEquals("Expected one String, containing JSON as parameter", thrown.getMessage());
    }

}
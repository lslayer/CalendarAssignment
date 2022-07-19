package tk.vgog.calendar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmptyHolidayProviderTest {

    final HolidayProvider provider = new EmptyHolidayProvider();

    @Test
    @DisplayName("Clear holidays works")
    void whenClearHolidays_gotHolidaysEmpty() {
        provider.clearHolidays();
        assertTrue(provider.getHolidays().isEmpty());
    }

    @Test
    @DisplayName("Holidays always empty")
    void whenFillHolidays_gotHolidaysAlwaysEmpty() {
        provider.fillHolidays("20220701");
        assertTrue(provider.getHolidays().isEmpty());
    }
}
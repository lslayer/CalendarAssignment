package tk.vgog;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class EmptyHolidayProvider implements HolidayProvider {
    @Override
    public void clearHolidays() {
        //Nothing here
    }
    @Override
    public List<LocalDate> getHolidays() {
        return new ArrayList<>();
    }

    @Override
    public void fillHolidays(Object... args) {
        //Nothing here
    }
}

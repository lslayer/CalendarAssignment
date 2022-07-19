package tk.vgog.calendar;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;

public class CalendarConfiguration {
    private List<DayOfWeek> offDays = Arrays.asList(DayOfWeek.SUNDAY, DayOfWeek.SATURDAY);

    public CalendarConfiguration() {
    }

    public CalendarConfiguration(List<DayOfWeek> offDays) {
        this.offDays = offDays;
    }

    public List<DayOfWeek> getOffDays() {
        return offDays;
    }

}

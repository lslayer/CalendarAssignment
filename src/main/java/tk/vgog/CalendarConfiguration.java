package tk.vgog;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;

public class CalendarConfiguration {
    private List<DayOfWeek> offDays = Arrays.asList(DayOfWeek.SUNDAY, DayOfWeek.SATURDAY);

    public List<DayOfWeek> getOffDays() {
        return offDays;
    }

    public void setOffDays(List<DayOfWeek> offDays) {
        this.offDays = offDays;
    }
}

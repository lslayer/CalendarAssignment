package tk.vgog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class JSONHolidayProvider implements HolidayProvider {
    public static final String HOLIDAYS_KEY = "holidays";
    private static final String DATE_FORMAT = "yyyyMMdd";
    private final List<LocalDate> holidays = new ArrayList<>();

    public void fillHolidays(Object... jsons) {
        String externalJson;
        try {
            externalJson = (String) jsons[0];
        } catch (Exception e) {
            throw new InvalidParameterException("Expected one String, containing JSON as parameter");
        }
        JSONObject external = new JSONObject(externalJson);
        JSONArray holidays = external.getJSONArray(HOLIDAYS_KEY);
        for (Object node : holidays) {
            LocalDate holiday = LocalDate.parse(String.valueOf(node), DateTimeFormatter.ofPattern(DATE_FORMAT));
            this.holidays.add(holiday);
        }
    }
    @Override
    public void clearHolidays() {
        this.holidays.clear();
    }

    @Override
    public List<LocalDate> getHolidays() {
        return holidays;
    }
}

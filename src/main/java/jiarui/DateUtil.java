package jiarui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

    private static final DateTimeFormatter IN_DATE = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final DateTimeFormatter IN_DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    private static final DateTimeFormatter OUT_DATE = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter OUT_DATE_TIME = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public static LocalDateTime parseToDateTime(String raw) throws JiaruiException {
        String s = raw.trim();
        try {
            return LocalDateTime.parse(s, IN_DATE_TIME);
        } catch (DateTimeParseException ignored) {
            try {
                LocalDate d = LocalDate.parse(s, IN_DATE);
                return d.atStartOfDay();
            } catch (DateTimeParseException e) {
                throw new JiaruiException("No! Please use yyyy-MM-dd or yyyy-MM-dd HHmm (e.g. 2019-12-02 1800).");
            }
        }
    }

    public static String format(LocalDateTime dt) {
        if (dt.toLocalTime().equals(LocalTime.MIDNIGHT)) {
            return dt.toLocalDate().format(OUT_DATE);
        }
        return dt.format(OUT_DATE_TIME);
    }
}

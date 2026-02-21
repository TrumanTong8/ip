package jiarui;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void event_toString_notDone_success() {
        LocalDateTime start = LocalDateTime.of(2026, 8, 6, 14, 0);
        LocalDateTime end = LocalDateTime.of(2026, 8, 6, 16, 0);

        Event e = new Event(false, "project meeting", start, end);
        // Used ChatGPT to improve and verify test case
        assertEquals("[E][ ] project meeting (from: Aug 06 2026 14:00 to: Aug 06 2026 16:00)", e.toString());
    }

    @Test
    public void event_toString_done_success() {
        LocalDateTime start = LocalDateTime.of(2026, 8, 6, 14, 0);
        LocalDateTime end = LocalDateTime.of(2026, 8, 6, 16, 0);

        Event e = new Event(true, "project meeting", start, end);

        assertEquals("[E][X] project meeting (from: Aug 06 2026 14:00 to: Aug 06 2026 16:00)", e.toString());
    }

    @Test
    public void event_getters_success() {
        LocalDateTime start = LocalDateTime.of(2026, 8, 6, 14, 0);
        LocalDateTime end = LocalDateTime.of(2026, 8, 6, 16, 0);

        Event e = new Event(false, "project meeting", start, end);

        assertEquals(start, e.getStart());
        assertEquals(end, e.getEnd());
    }

    @Test
    public void event_markAsCompleted_updatesToString() {
        LocalDateTime start = LocalDateTime.of(2026, 8, 6, 14, 0);
        LocalDateTime end = LocalDateTime.of(2026, 8, 6, 16, 0);

        Event e = new Event(false, "project meeting", start, end);
        e.markAsCompleted();

        assertEquals("[E][X] project meeting (from: Aug 06 2026 14:00 to: Aug 06 2026 16:00)", e.toString());
    }



}

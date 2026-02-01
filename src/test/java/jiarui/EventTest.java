package jiarui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void event_toString_notDone_success() {
        Event e = new Event(false, "project meeting", "Aug 6th 2pm", "4pm");
        assertEquals("[E][ ] project meeting (from: Aug 6th 2pm to: 4pm)", e.toString());
    }

    @Test
    public void event_toString_done_success() {
        Event e = new Event(true, "project meeting", "Aug 6th 2pm", "4pm");
        assertEquals("[E][X] project meeting (from: Aug 6th 2pm to: 4pm)", e.toString());
    }

    @Test
    public void event_getters_success() {
        Event e = new Event(false, "project meeting", "Aug 6th 2pm", "4pm");
        assertEquals("Aug 6th 2pm", e.getStart());
        assertEquals("4pm", e.getEnd());
    }

    @Test
    public void event_markAsCompleted_updatesToString() {
        Event e = new Event(false, "project meeting", "Aug 6th 2pm", "4pm");
        e.markAsCompleted();
        assertEquals("[E][X] project meeting (from: Aug 6th 2pm to: 4pm)", e.toString());
    }



}

package jiarui;

import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(boolean isCompleted, String description, LocalDateTime start, LocalDateTime end) {
            super(isCompleted, description);
            this.start = start;
            this.end = end;
    }

    /**
     * Returns the starting date of the Event
     *
     * @return The starting date of the Event
     */
    public LocalDateTime getStart() {
        return this.start;
    }

    /**
     * Returns the ending date of the Event
     *
     * @return The ending date of the Event
     */
    public LocalDateTime getEnd(){
            return this.end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateUtil.format(start) + " to: " + DateUtil.format(end)  +")";
    }
}

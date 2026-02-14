package jiarui;

import java.time.LocalDateTime;

public class Deadline extends Task {

    private LocalDateTime by;

    public Deadline(boolean isCompleted, String description, LocalDateTime by) {
        super(isCompleted, description);
        this.by = by;
    }

    /**
     * Returns the date in LocalDateTime format of by
     *
     * @return LocalDateTime of by
     */
    public LocalDateTime getBy(){
            return this.by;
    }

    public void editDate(LocalDateTime time){
        this.by = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateUtil.format(by) + ")";
    }
}


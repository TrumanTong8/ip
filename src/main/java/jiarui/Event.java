package jiarui;

public class Event extends Task {

        protected String start;
        protected String end;

        public Event(boolean isCompleted, String description, String start, String end) {
            super(isCompleted, description);
            this.start = start;
            this.end = end;
        }

        public String getStart() {
            return this.start;
        }

        public String getEnd(){
            return this.end;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + start + " to: " + end  +")";
        }

}

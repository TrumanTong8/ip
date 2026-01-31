public class Deadline extends Task {

        protected String by;

        public Deadline(boolean isCompleted, String description, String by) {
            super(isCompleted, description);
            this.by = by;
        }

        public String getBy(){
            return this.by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
}


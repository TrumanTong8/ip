package jiarui;

/**
 * Represents a Todo task. A <code>ToDo</code> object corresponds to
 * a task represented by a description e.g., <code>eat</code>
 * and whether it is completed eg <code> true </code>
 */
public class ToDo extends Task{

    ToDo(boolean isCompleted, String description) {
        super(isCompleted, description);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

}

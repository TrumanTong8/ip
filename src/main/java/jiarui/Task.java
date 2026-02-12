package jiarui;

/**
 * Represents an Event task. An <code>Event</code> object corresponds to
 * a task represented by a description e.g., <code>eat</code>
 * and whether it is completed eg <code> true </code>
 */

public class Task {
    boolean completed;
    String description;

    Task(boolean isCompleted, String description){
        assert description != null : "Task description should not be null";

        this.completed = isCompleted;
        this.description = description;
    }

    /**
     * Returns the String associated to whether the task is completed
     * eg "X" for Completed and " " for not completed
     *
     * @return the String associated to whether the task si completed
     */
    public String getStatusIcon(){
        return(completed? "X" : " ");
    }

    /**
     * Marks the current Task as completed
     *
     */
    public void markAsCompleted(){
        completed = true;
        System.out.println("____________________________________________________________");
        String s = "Nice! I've marked this task as done:";
        System.out.println(s);
        System.out.println(this.toString());
        System.out.println("____________________________________________________________");
    }

    /**
     * Marks the current Task as uncompleted
     *
     */
    public void markAsNotCompleted(){
        completed = false;
        System.out.println("____________________________________________________________");
        String s = "OK, I've marked this task as not done yet:";
        System.out.println(s);
        System.out.println(this.toString());
        System.out.println("____________________________________________________________");


    }

    /**
     * Returns the String description of the Task
     *
     * @return The description of the Task
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Returns the boolean value of whether the Task is completed
     *
     * @return The boolean value of the completion ofthe task
     */
    public boolean isDone(){
        return this.completed;
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
package jiarui;

import java.util.List;
import java.util.Scanner;

/**
 * Represents the UI portion of JiaRui
 *
 */
public class Ui {
    private static final String LINE = "____________________________________________________________\n";
    private final Scanner in = new Scanner(System.in);

    /**
     * This is the intro line everytime the UI starts up
     *
     */
    public void showIntro() {
        System.out.println(LINE);
        System.out.println("Hello! I'm JiaRui");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Returns the String of the command read
     *
     * @return String of the next command
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Prints the error message when the wrong input is used
     *
     * @param message The String of the error message
     */
    public void showError(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    /**
     * Shows a list of the Task
     *
     * @param tasks The list of Task currently in the list
     */
    public void showTaskList(List<Task> tasks) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println(LINE);
    }

    /**
     * Prints the message after a Task has been added
     *
     * @param task The type of Task that is added
     * @param size The total size of the Task List
     */
    public void showAdded(Task task, int size) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Prints the Task deleted along with its message
     *
     * @param task The task that was deleted
     * @param size The updated size of the Task List
     */
    public void showDeleted(Task task, int size) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Shows the list of Task that are matched according to the keyword
     *
     * @param matches The list of matched Task containing the keyword
     */
    public void showMatchingTasks(List<Task> matches){
        System.out.println(LINE);
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matches.size(); i ++){
            System.out.println((i+1) + ". " + matches.get(i));
        }
        System.out.println(LINE);
    }

    /**
     * Shows the ending message after the user types 'bye'
     *
     */
    public void showGoodbye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}

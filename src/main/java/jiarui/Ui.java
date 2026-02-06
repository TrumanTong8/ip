package jiarui;

import java.util.List;
import java.util.Scanner;

/**
 * Represents the UI portion of JiaRui.
 * <p>
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private final Scanner in = new Scanner(System.in);

    /**
     * Reads the next command from standard input
     * <p>
     * This is intended for CLI mode only. In GUI mode, user input is handled by JavaFX controls
     *
     * @return The next line entered by the user
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Returns the intro message shown when the app starts
     *
     * @return Formatted intro message
     */
    public String formatIntro() {
        return LINE + "\n"
                + "Hello! I'm JiaRui\n"
                + "What can I do for you?\n"
                + LINE;
    }

    /**
     * Prints the intro message to the console (CLI mode)
     */
    public void showIntro() {
        System.out.println(formatIntro());
    }

    /**
     * Returns a formatted error message.
     *
     * @param message The error message content.
     * @return Formatted error message.
     */
    public String formatError(String message) {
        return message;
    }

    /**
     * Prints a formatted error message to the console (CLI mode).
     *
     * @param message The error message content.
     */
    public void showError(String message) {
        System.out.println(formatError(message));
    }

    /**
     * Returns a formatted view of the full task list.
     *
     * @param tasks The list of tasks to display.
     * @return Formatted task list message.
     */
    public String formatTaskList(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Prints the full task list to the console (CLI mode).
     *
     * @param tasks The list of tasks to display.
     */
    public void showTaskList(List<Task> tasks) {
        System.out.println(formatTaskList(tasks));
    }

    /**
     * Returns the message shown after a task has been added.
     *
     * @param task The task that was added.
     * @param size The updated size of the task list.
     * @return Formatted "task added" message.
     */
    public String formatAdded(Task task, int size) {
        return "Got it. I've added this task:\n"
                + "  " + task + "\n"
                + "Now you have " + size + " tasks in the list.\n";
    }

    /**
     * Prints the message after a task has been added (CLI mode).
     *
     * @param task The task that was added.
     * @param size The updated size of the task list.
     */
    public void showAdded(Task task, int size) {
        System.out.println(formatAdded(task, size));
    }

    /**
     * Returns the message shown after a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param size The updated size of the task list.
     * @return Formatted "task deleted" message.
     */
    public String formatDeleted(Task task, int size) {
        return "Noted. I've removed this task:\n"
                + "  " + task + "\n"
                + "Now you have " + size + " tasks in the list.\n";
    }

    /**
     * Prints the message after a task has been deleted (CLI mode).
     *
     * @param task The task that was deleted.
     * @param size The updated size of the task list.
     */
    public void showDeleted(Task task, int size) {
        System.out.println(formatDeleted(task, size));
    }

    /**
     * Returns the formatted list of tasks that match a keyword.
     *
     * @param matches The list of matched tasks.
     * @return Formatted "matching tasks" message.
     */
    public String formatMatchingTasks(List<Task> matches) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matches.size(); i++) {
            sb.append(i + 1).append(". ").append(matches.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Prints the list of matched tasks to the console (CLI mode).
     *
     * @param matches The list of matched tasks.
     */
    public void showMatchingTasks(List<Task> matches) {
        System.out.println(formatMatchingTasks(matches));
    }

    /**
     * Returns the goodbye message shown when the user exits.
     *
     * @return Formatted goodbye message.
     */
    public String formatGoodbye() {
                return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Prints the goodbye message to the console (CLI mode).
     */
    public void showGoodbye() {
        System.out.println(formatGoodbye());
    }
}

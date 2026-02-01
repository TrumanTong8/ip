import java.util.List;
import java.util.Scanner;

public class Ui {
    private static final String LINE = "____________________________________________________________\n";
    private final Scanner in = new Scanner(System.in);

    public void showIntro() {
        System.out.println(LINE);
        System.out.println("Hello! I'm JiaRui");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public String readCommand() {
        return in.nextLine();
    }

    public void showError(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    public void showTaskList(List<Task> tasks) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println(LINE);
    }

    public void showAdded(Task task, int size) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(LINE);
    }

    public void showDeleted(Task task, int size) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(LINE);
    }

    public void showGoodbye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}

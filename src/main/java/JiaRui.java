import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class JiaRui {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        String name = "____________________________________________________________\n"
                + "Hello! I'm JiaRui\n"
                + "What can I do for you?\n"
                + "____________________________________________________________";


        System.out.println(name);
        String line = in.nextLine();

        List<Task> list = new ArrayList<>();

        while (!line.equals("bye")) {

            try {
                String[] parts = line.trim().split(" ", 2);
                String cmd = parts[0];

                if (cmd.equals("list")) {
                    System.out.println("____________________________________________________________\n"
                            + "Here are the tasks in your list:");

                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i + 1) + ". " + list.get(i).toString());
                    }
                    System.out.println("____________________________________________________________");

                } else if (cmd.equals("mark") || cmd.equals("unmark")) {

                    if (parts.length < 2){
                        throw new JiaRuiException("Please provide a task number");
                    }

                    int taskNum = Integer.parseInt(parts[1]);
                    int index = taskNum - 1;

                    if (index < 0 || index >= list.size()) {
                        throw new JiaRuiException("That is an invalid task number!");
                    } else {
                        Task t = list.get(index);

                        if (cmd.equals("mark")) {
                            t.markAsCompleted();
                        } else {
                            t.markAsNotCompleted();
                        }
                    }
                } else {

                    Task task;

                    if (cmd.equals("todo")) {
                        if (parts.length < 2 || parts[1].trim().isEmpty()) {
                            throw new JiaRuiException("No! The description of a todo cannot be empty.");
                        }
                        task = new ToDo(parts[1]);

                    } else if (cmd.equals("deadline")) {
                        if (parts.length < 2 || parts[1].trim().isEmpty()) {
                            throw new JiaRuiException("No! The description of a deadline cannot be empty.");
                        }

                        String[] description = parts[1].split(" /by ", 2);

                        if (description.length < 2) throw new JiaRuiException("No! A deadline must have /by.");

                        task = new Deadline(description[0], description[1]);
                    } else if (cmd.equals("event")) {
                        if (parts.length < 2) throw new JiaRuiException("No! The description of an event cannot be empty.");

                        String[] description = parts[1].split(" /from | /to", 3);

                        if (description.length < 3) throw new JiaRuiException("No! An event must have /from and /to.");
                        task = new Event(description[0], description[1], description[2]);
                    } else {
                        throw new JiaRuiException("That is not a valid task!");
                    }


                    list.add(task);

                    System.out.println("____________________________________________________________\n"
                            + "Got it. I've added this task: \n"
                            + task
                            + "\n"
                            + "Now you have " + list.size() + " task in the list.\n"
                            + "____________________________________________________________\n");

                }




            } catch (JiaRuiException e) {
                System.out.println("____________________________________________________________\n"
                        + e.getMessage() + "\n"
                        + "____________________________________________________________");
            }

            line = in.nextLine();

        }

        System.out.println("____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n");

    }
}
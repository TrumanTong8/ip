import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class JiaRui {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        String name = "____________________________________________________________\n"
                + "Hello! I'm JiaRui\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";


        System.out.println(name);
        String line = in.nextLine();

        List<Task> list = new ArrayList<>();

        while(!line.equals("bye")){
            String[] parts = line.trim().split("\\s+");
            String cmd = parts[0];

            if(cmd.equals("list")){
                System.out.println("____________________________________________________________\n"
                        + "Here are the tasks in your list:" );

                for(int i = 0; i < list.size(); i++){
                    System.out.println( (i + 1) +". " + list.get(i).toString());
                }
                System.out.println("____________________________________________________________");

            } else if (cmd.equals("mark") || cmd.equals("unmark")){
                int taskNum = Integer.parseInt(parts[1]);
                int index = taskNum - 1;

                if (index < 0 || index >= list.size()){
                    System.out.println("Invalid task number");
                } else {
                    Task t = list.get(index);

                    if (cmd.equals("mark")){
                        t.markAsCompleted();
                    } else {
                        t.markAsNotCompleted();
                    }
                }
            }else {

                System.out.println("____________________________________________________________\n"
                        + "added: "
                        + line
                        + "\n____________________________________________________________\n");

                list.add(new Task(line));
            }

            line = in.nextLine();
        }

        System.out.println("____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n");

    }
}
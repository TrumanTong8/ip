import java.util.Scanner;

public class JiaRui {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        String name = "____________________________________________________________\n"
                + "Hello! I'm JiaRui\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";


        System.out.println(name);
        String line = in.nextLine();

        while(!line.equals("bye")){
            System.out.println("____________________________________________________________\n"
                + line
                + "\n____________________________________________________________\n");

            line = in.nextLine();
        }

        System.out.println("____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n");

    }
}
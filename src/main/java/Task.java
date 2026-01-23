public class Task {
    boolean completed;
    String description;

    Task(String description){
        this.completed = false;
        this.description = description;
    }

    public String getStatusIcon(){
        return(completed? "X" : " ");
    }

    public void markAsCompleted(){
        completed = true;
        System.out.println("____________________________________________________________");
        String s = "Nice! I've marked this task as done:";
        System.out.println(s);
        System.out.println(this.toString());
        System.out.println("____________________________________________________________");


    }

    public void markAsNotCompleted(){
        completed = false;
        System.out.println("____________________________________________________________");
        String s = "OK, I've marked this task as not done yet:";
        System.out.println(s);
        System.out.println(this.toString());
        System.out.println("____________________________________________________________");


    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + this.description;
    }
}

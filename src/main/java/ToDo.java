public class ToDo extends Task{

    ToDo(boolean isCompleted, String description) {
        super(isCompleted, description);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

}

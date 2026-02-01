import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList(){
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks){
        this.tasks = tasks;
    }

    public List<Task> asUnmodifiableList() {
        return List.copyOf(tasks);
    }

    public int size() {
        return tasks.size();
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public Task delete(int index) {
        return tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }


}

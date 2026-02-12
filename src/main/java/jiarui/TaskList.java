package jiarui;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Task List. A <code>Task List</code> object corresponds to
 * the tasks represented by a list
 */
public class TaskList {
    private final List<Task> tasks;

    public TaskList(){
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks){
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks
     *
     * @return the list of tasks in the current Task List object
     */
    public List<Task> asUnmodifiableList() {
        return List.copyOf(tasks);
    }

    /**
     * Returns the size of the Task List object
     *
     * @return the numeric value of the number of Task in the Task List
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Adds a Tasks t into the Task List Object
     *
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     *
     * @param index The number location of which that task will be deleted
     *
     * @return The Task that got deleted
     */
    public Task delete(int index) {
        return tasks.remove(index);
    }

    /**
     *
     * @param index The number location of which that task will be fetched
     *
     * @return The Task that got fetched
     */
    public Task get(int index) {
        assert index >= 0 && (index < tasks.size()) : "index must be valid when calling get()";
        return tasks.get(index);
    }

    /**
     * Returns the List of Task that have the keyword in the description
     *
     * @param keyword The word that is used to filter the task description
     * @return List of Task that have the keyword
     */
    public List<Task> findByKeyword(String keyword){
        List<Task> matches = new ArrayList<>();
        String k = keyword.toLowerCase();

        for (Task t: tasks){
            if (t.getDescription().toLowerCase().contains(k)){
                matches.add(t);
            }
        }
        return matches;
    }
}

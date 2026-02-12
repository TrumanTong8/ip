package jiarui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path filePath;

    public Storage() {
        this.filePath = Paths.get("src","data", "jiarui.txt");
    }

    /**
     * Loads the Tasks stored from the previous instance JiaRui was used
     *
     * @return The Task List object of the list of Task
     */
    public List<Task> load(){
        List<Task> tasks = new ArrayList<>();

        if (!Files.exists((filePath))){
            return tasks;
        }

        try {
            List<String> lines = Files.readAllLines(filePath);

            for (String line: lines){
                if (line.trim().isEmpty()){
                    continue;
                }

                try{
                    Task t = parseLine(line);
                    tasks.add(t);
                } catch (Exception e) {
                }
            }
        } catch (IOException e){

        }

        return tasks;
    }

    /**
     * Saves the list of Tasks into data/jiarui.txt for future reference
     *
     * @param tasks The tasks to be stored
     * @throws IOException if there is no such file
     */
    public void save(List<Task> tasks) throws IOException {
        Path parent = filePath.getParent();
        if (parent != null){
            Files.createDirectories(parent);
        }

        List<String> lines = new ArrayList<>();
        for (Task t : tasks) {
            lines.add(encodeLine(t));
        }

        Files.write(filePath, lines,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }

    /**
     * Returns the String of the task to be saved
     *
     * @param t The Task object to be saved
     * @return The String encode of the Task
     */
    private String encodeLine(Task t) {
        String done = t.isDone() ? "1" : "0";

        if (t instanceof ToDo) {
            return "T | " + done + " | " + t.getDescription();
        } else if (t instanceof Deadline d) {
            return "D | " + done + " | " + d.getDescription() + " | " + d.getBy();
        } else if (t instanceof Event e) {
            return "E | " + done + " | " + e.getDescription() + " | " + e.getStart() + " | " + e.getEnd();
        } else {
            return "T | " + done + " | " + t.getDescription();
        }
    }

    /**
     * Returns the Task from the saved file
     *
     * @param line The line from JiaRui.txt
     * @return The Task object
     * @throws JiaRuiException if the Task saved was not properly saved
     */
    private Task parseLine(String line) throws JiaRuiException {
        String[] parts = line.split("\\s*\\|\\s*");
        assert parts.length >= 3 : "Saved line must have at least type | done | desc";

        String type = parts[0];
        assert type.equals("T") || type.equals("D") || type.equals("E") : "Unknown task type in storage: " + type;

        boolean done = parts[1].equals("1");
        String desc = parts[2];

        Task task;
        if (type.equals("T")) {
            task = new ToDo(done, desc);
        } else if (type.equals("D")) {
            LocalDateTime by = DateUtil.parseToDateTime(parts[3]);
            task = new Deadline(done, desc, by);
        } else if (type.equals("E")) {
            String from = parts[3];
            String to = parts[4];
            task = new Event(done, desc, from, to);
        } else {
            throw new IllegalArgumentException("Unknown task type");
        }

        return task;
    }

}

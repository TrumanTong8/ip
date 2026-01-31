import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path filePath;

    public Storage() {
        this.filePath = Paths.get("src","data", "jiarui.txt");
    }

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

    private Task parseLine(String line) {
        String[] p = line.split("\\s*\\|\\s*");

        String type = p[0];
        boolean done = p[1].equals("1");
        String desc = p[2];

        Task task;
        if (type.equals("T")) {
            task = new ToDo(done, desc);
        } else if (type.equals("D")) {
            String by = p[3];
            task = new Deadline(done, desc, by);
        } else if (type.equals("E")) {
            String from = p[3];
            String to = p[4];
            task = new Event(done, desc, from, to);
        } else {
            throw new IllegalArgumentException("Unknown task type");
        }

        return task;
    }

}

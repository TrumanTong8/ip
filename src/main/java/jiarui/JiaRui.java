package jiarui;

import java.io.IOException;
import java.util.List;

public class JiaRui {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public JiaRui() {
        ui = new Ui();
        storage = new Storage();
        List<Task> loaded;

        try {
            loaded = storage.load();
        } catch (Exception e) {
            loaded = List.of();
            ui.showError("Shaggers! Could not load saved tasks.");
        }
        tasks = new TaskList(loaded);
    }

    public void run() {
        ui.showIntro();

        while (true) {
            String line = ui.readCommand();
            ParsedCommand cmd = Parser.parse(line);

            if (cmd.keyword.equals("bye")) {
                ui.showGoodbye();
                break;
            }

            try {
                execute(cmd);
            } catch (JiaRuiException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    private void execute(ParsedCommand cmd) throws JiaRuiException {
        switch (cmd.keyword) {
            case "list":
                ui.showTaskList(tasks.asUnmodifiableList());
                break;

            case "mark":
                markUnmark(cmd.args, true);
                saveNow();
                break;

            case "unmark":
                markUnmark(cmd.args, false);
                saveNow();
                break;

            case "delete":
                delete(cmd.args);
                saveNow();
                break;

            case "find":
                if (cmd.args.isEmpty()){
                    throw new JiaRuiException("No! Please provide a keyword to find.");
                }
                List<Task> matches = tasks.findByKeyword(cmd.args);
                ui.showMatchingTasks(matches);
                break;

            case "todo":
            case "deadline":
            case "event":
                addTask(cmd.keyword, cmd.args);
                saveNow();
                break;


            default:
                throw new JiaRuiException("No! I'm sorry, but I don't know what that means :-(");
        }
    }

    private void markUnmark(String args, boolean done) throws JiaRuiException {
        if (args.isEmpty()){
            throw new JiaRuiException("No! Please provide a task number.");
        }
        int idx = Parser.parseIndex(args);

        if (idx < 0 || idx >= tasks.size()){
            throw new JiaRuiException("No! Invalid task number.");
        }

        Task t = tasks.get(idx);
        if (done){
            t.markAsCompleted();
        }
        else {
            t.markAsNotCompleted();
        }
    }

    private void delete(String args) throws JiaRuiException {
        if (args.isEmpty()){
            throw new JiaRuiException("No! Please provide a task number to delete.");
        }
        int idx = Parser.parseIndex(args);

        if (idx < 0 || idx >= tasks.size()){
            throw new JiaRuiException("No! Invalid task number.");
        }
        Task removed = tasks.delete(idx);
        ui.showDeleted(removed, tasks.size());
    }

    private void addTask(String keyword, String args) throws JiaRuiException {
        if (args.isEmpty()){
            throw new JiaRuiException("No! The description cannot be empty.");
        }

        Task task;
        if (keyword.equals("todo")) {
            task = new ToDo(false, args);

        } else if (keyword.equals("deadline")) {
            String[] p = args.split(" /by ", 2);
            if (p.length < 2){
                throw new JiaRuiException("No! A deadline must have /by.");
            }
            task = new Deadline(false, p[0].trim(), DateUtil.parseToDateTime(p[1]));

        } else {
            String[] p = args.split(" /from | /to", 3);
            if (p.length < 3){
                throw new JiaRuiException("No! An event must have /from and /to.");
            }
            task = new Event(false, p[0].trim(), p[1].trim(), p[2].trim());
        }

        tasks.add(task);
        ui.showAdded(task, tasks.size());
    }

    private void saveNow() throws JiaRuiException {
        try {
            storage.save(tasks.asUnmodifiableList());
        } catch (IOException e) {
            throw new JiaRuiException("No! I couldn't save your tasks.");
        }
    }

    public static void main(String[] args) {
        new JiaRui().run();
    }
}

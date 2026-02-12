package jiarui;

import java.io.IOException;
import java.util.List;

public class JiaRui {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private boolean isExit = false;

    public JiaRui() {
        ui = new Ui();
        storage = new Storage();
        List<Task> loaded;
        try {
            loaded = storage.load();
        } catch (Exception e) {
            loaded = List.of();
        }
        tasks = new TaskList(loaded);
    }

    public String getIntro(){
        return this.ui.formatIntro();
    }

    public String getResponse(String input) {
        ParsedCommand cmd = Parser.parse(input);

        try {
            return execute(cmd);
        } catch (JiaRuiException e) {
            return ui.formatError(e.getMessage());
        } catch (Exception e) {
            return ui.formatError("No!!! Something went wrong.");
        }
    }

    public boolean isExit() {
        return isExit;
    }

    private boolean cmdChangesData(String keyword) {
        return keyword.equals("todo") || keyword.equals("deadline") || keyword.equals("event")
                || keyword.equals("mark") || keyword.equals("unmark") || keyword.equals("delete");
    }

    private String execute(ParsedCommand cmd) throws JiaRuiException {
        String msg;

        switch (cmd.keyword) {
        case "bye":
            isExit = true;
            return ui.formatGoodbye();

        case "list":
            return ui.formatTaskList(tasks.asUnmodifiableList());

        case "find":
            if (cmd.args.isEmpty()) throw new JiaRuiException("OOPS!!! Please provide a keyword to find.");
            return ui.formatMatchingTasks(tasks.findByKeyword(cmd.args));

        case "mark":
            msg = markUnmark(cmd.args, true);
            saveNow();
            return msg;

        case "unmark":
            msg = markUnmark(cmd.args, false);
            saveNow();
            return msg;

        case "delete":
            msg = delete(cmd.args);
            saveNow();
            return msg;

        case "todo":
        case "deadline":
        case "event":
            msg = addTask(cmd.keyword, cmd.args);
            saveNow();
            return msg;
        default:
            throw new JiaRuiException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private String markUnmark(String args, boolean done) throws JiaRuiException {
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
             return ui.formatMarked(t);

        }
        else {
            t.markAsNotCompleted();
            return ui.formatUnmarked(t);
        }
    }

    private String delete(String args) throws JiaRuiException {
        if (args.isEmpty()){
            throw new JiaRuiException("No! Please provide a task number to delete.");
        }
        int idx = Parser.parseIndex(args);

        if (idx < 0 || idx >= tasks.size()){
            throw new JiaRuiException("No! Invalid task number.");
        }
        Task removed = tasks.delete(idx);
        return ui.formatDeleted(removed, tasks.size());
    }

    private String addTask(String keyword, String args) throws JiaRuiException {
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
        return ui.formatAdded(task, tasks.size());
    }

    private void saveNow() throws JiaRuiException {
        try {
            storage.save(tasks.asUnmodifiableList());
        } catch (IOException e) {
            throw new JiaRuiException("No! I couldn't save your tasks.");
        }
    }

}


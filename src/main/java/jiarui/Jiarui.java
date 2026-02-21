package jiarui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class Jiarui {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private boolean isExit = false;

    public Jiarui() {
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
        } catch (JiaruiException e) {
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

    private String execute(ParsedCommand cmd) throws JiaruiException {
        assert cmd != null : "cmd should not be null";
        assert cmd.keyword != null : "cmd.keyword should not be null";
        assert cmd.args != null : "cmd.args should not be null";

        String msg;

        switch (cmd.keyword) {
        case "bye":
            isExit = true;
            return ui.formatGoodbye();

        case "list":
            return ui.formatTaskList(tasks.asUnmodifiableList());

        case "find":
            if (cmd.args.isEmpty()){
                throw new JiaruiException("OOPS!!! Please provide a keyword to find.");
            }
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

        case "reschedule":
            msg = changeDate(cmd.args);
            saveNow();
            return msg;

        case "todo":
        case "deadline":
        case "event":
            msg = addTask(cmd.keyword, cmd.args);
            saveNow();
            return msg;
        default:
            throw new JiaruiException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private String changeDate(String args) throws JiaruiException{
        if (args.isEmpty()){
            throw new JiaruiException("No! Please provide a task number.");
        }

        String[] p = args.split("\\s+", 2);
        int idx = Parser.parseIndex(p[0]);

        if (idx < 0 || idx >= tasks.size()){
            throw new JiaruiException("No! Invalid task number.");
        }

        Task t = tasks.get(idx);
        if (!(t instanceof Deadline)){
            throw new JiaruiException("No! Only deadline can be rescheduled.");
        }

        String newByStr = p[1].trim();
        if (newByStr.isEmpty()){
            throw new JiaruiException("No! The new deadline cannot be empty.");
        }

        LocalDateTime newBy = DateUtil.parseToDateTime(newByStr);
        Deadline d = (Deadline) t;
        d.editDate(newBy);

        return ui.formatChanged(t);
    }

    private String markUnmark(String args, boolean done) throws JiaruiException {
        if (args.isEmpty()){
            throw new JiaruiException("No! Please provide a task number.");
        }
        int idx = Parser.parseIndex(args);

        if (idx < 0 || idx >= tasks.size()){
            throw new JiaruiException("No! Invalid task number.");
        }

        Task t = tasks.get(idx);
        if (done){
            t.markAsCompleted();
             return ui.formatMarked(t);

        } else {
            t.markAsNotCompleted();
            return ui.formatUnmarked(t);
        }
    }

    private String delete(String args) throws JiaruiException {
        if (args.isEmpty()){
            throw new JiaruiException("No! Please provide a task number to delete.");
        }
        int idx = Parser.parseIndex(args);

        if (idx < 0 || idx >= tasks.size()){
            throw new JiaruiException("No! Invalid task number.");
        }
        Task removed = tasks.delete(idx);
        return ui.formatDeleted(removed, tasks.size());
    }

    private String addTask(String keyword, String args) throws JiaruiException {
        if (args.isEmpty()){
            throw new JiaruiException("No! The description cannot be empty.");
        }

        Task task;
        if (keyword.equals("todo")) {
            task = new ToDo(false, args);

        } else if (keyword.equals("deadline")) {
            String[] p = args.split(" /by ", 2);
            if (p.length < 2){
                throw new JiaruiException("No! A deadline must have /by.");
            }
            task = new Deadline(false, p[0].trim(), DateUtil.parseToDateTime(p[1]));

        } else {
            String[] p = args.split(" /from | /to", 3);
            if (p.length < 3){
                throw new JiaruiException("No! An event must have /from and /to.");
            }
            task = new Event(false, p[0].trim(),
                    DateUtil.parseToDateTime(p[1].trim()), DateUtil.parseToDateTime(p[2].trim()));
        }

        tasks.add(task);
        return ui.formatAdded(task, tasks.size());
    }

    private void saveNow() throws JiaruiException {
        try {
            storage.save(tasks.asUnmodifiableList());
        } catch (IOException e) {
            throw new JiaruiException("No! I couldn't save your tasks.");
        }
    }

}


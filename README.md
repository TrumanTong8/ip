# Jiarui 👨‍💻📝 — Task Manager Chatbot

Jiarui is a kind and happy simple task manager that helps you track todos, deadlines, and events using short command!

---
![Ui.png](Ui.png)

--- 
## Quick Start

### Requirements
- **Java 17** installed

### Run (JAR)
1. Download `Jiarui.jar`
2. Open a terminal in the same folder
3. Run:

```bash
java -jar Jiarui.jar
```
---

## 📋 Command Summary

| Command      | Description                          |
|--------------|--------------------------------------|
| `todo`       | Add a to-do task                     |
| `deadline`   | Add a task with a deadline           |
| `event`      | Add an event with start and end time |
| `list`       | View all tasks                       |
| `mark`       | Mark a task as done                  |
| `unmark`     | Mark a task as not done              |
| `find`       | Find tasks by keyword            |
| `reschedule` | Reschedule a deadline task           |
| `delete`     | Delete a task                        |
| `bye`        | Exit the application                 |


---

# ✨ Features

## ➕ Adding a To-Do

Adds a simple task

### Format

```
todo <task description>
```

### Example

```
todo read book
```

---

## ⏰ Adding a Deadline

Adds a task that must be completed by a specific date and time.

### Format

```
deadline <task description> /by yyyy-MM-dd HHmm
```

### Example

```
deadline submit report /by 2026-02-20 1800
```

### Date Format

```
yyyy-MM-dd HHmm
```

Example:

```
2026-02-20 1800
```

---

## 📅 Adding an Event

Adds an event with a start and end date-time.

### Format

```
event <event description> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm
```

### Example

```
event team meeting /from 2026-02-20 1400 /to 2026-02-20 1600
```


---

## 📋 Viewing All Tasks

Displays all tasks in a numbered list.

```
list
```

---

## ✅ Marking a Task as Done

Marks a task as completed.

### Format
```
mark <task number>
```

### Example:

```
mark 2
```

---

## 🔄 Unmarking a Task

Marks a task as not completed.

### Format
```
unmark <task number>
```
### Example:

```
unmark 2
```

---

## 🕵🏻‍♂️ Find Tasks

Find tasks whose description contains a keyword (case-insensitive).

### Format
```
find <keyword>
```
### Example:

```
find book
```
---
## 📘 Reschedule Command

Reschedule a given Deadline Task.

### Format
```
reschedule <task number> <yyyy-MM-dd HHmm>
```
### Example:

```
reschedule 2 2016-12-02 1200
```
---

## 🗑 Deleting a Task

Deletes a task permanently.

### Format
```
delete <task number>
```
### Example:

```
delete 2
```
---

## 👋 Exiting the Application

Closes Jiarui.

```
bye
```

---

# 💾 Task Storage

- Tasks are automatically saved after every modification.
- When you reopen Jiarui, your tasks will still be available.

---

# 🚨 Error Handling

If an invalid command is entered, Jiarui will respond with an error message.

Common errors include:

- Missing date format
- Missing task number
- Invalid task index
- Incorrect command format

Ensure that commands follow the specified format exactly.

---

# 📝 Command Cheat Sheet

```
todo read book
deadline homework /by 2026-02-20 1800
event meeting /from 2026-02-20 1400 /to 2026-02-20 1600
list
mark 1
unmark 1
find book
reschedule 2 2026-12-02 1200
delete 2
bye
```

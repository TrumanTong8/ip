package jiarui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {
    @Test
    public void todo_toString_notDone_success() {
        assertEquals("[T][ ] eat", new ToDo(false, "eat").toString());
    }

    @Test
    public void todo_toString_done_success() {
        assertEquals("[T][X] eat", new ToDo(true, "eat").toString());
    }

    @Test
    public void todo_markDone_updatesToString() {
        ToDo t = new ToDo(false, "eat");
        t.markAsCompleted();
        assertEquals("[T][X] eat", t.toString());
    }

}

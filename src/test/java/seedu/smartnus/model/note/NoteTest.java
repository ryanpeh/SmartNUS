package seedu.smartnus.model.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.smartnus.testutil.TypicalNotes.CS2100_NOTE;
import static seedu.smartnus.testutil.TypicalNotes.CS2103T_NOTE;

import org.junit.jupiter.api.Test;

import seedu.smartnus.testutil.NoteBuilder;

public class NoteTest {
    @Test
    public void hashCode_test() {
        Note cs2103tNote = new NoteBuilder(CS2103T_NOTE).build();
        Note cs2103tCopy = new NoteBuilder(CS2103T_NOTE).build();

        assertEquals(cs2103tNote.hashCode(), cs2103tCopy.hashCode());

        Note cs2100Note = new NoteBuilder(CS2100_NOTE).build();
        assertNotEquals(cs2103tNote.hashCode(), cs2100Note.hashCode());
    }
}

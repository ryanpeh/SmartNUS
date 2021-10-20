package seedu.smartnus.model.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_NOTE_TAG_2;
import static seedu.smartnus.testutil.Assert.assertThrows;
import static seedu.smartnus.testutil.TypicalNotes.CS2100_NOTE;
import static seedu.smartnus.testutil.TypicalNotes.CS2103T_NOTE;

import org.junit.jupiter.api.Test;

import seedu.smartnus.testutil.NoteBuilder;

public class NoteTest {
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Note note = new NoteBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> note.getTags().remove(0));
    }

    @Test
    public void hashCode_test() {
        Note cs2103tNote = new NoteBuilder(CS2103T_NOTE).build();
        Note cs2103tCopy = new NoteBuilder(CS2103T_NOTE).build();

        assertEquals(cs2103tNote.hashCode(), cs2103tCopy.hashCode());

        Note cs2100Note = new NoteBuilder(CS2100_NOTE).build();
        assertNotEquals(cs2103tNote.hashCode(), cs2100Note.hashCode());

        // different tags -> returns false
        Note editedCs2103t = new NoteBuilder(CS2103T_NOTE).withTags(VALID_NOTE_TAG_2).build();
        assertNotEquals(cs2103tNote.hashCode(), editedCs2103t.hashCode());
    }
}

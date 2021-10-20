package seedu.smartnus.model.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_NOTE_TAG_1;
import static seedu.smartnus.testutil.Assert.assertThrows;
import static seedu.smartnus.testutil.TypicalNotes.CS2100_NOTE;
import static seedu.smartnus.testutil.TypicalNotes.CS2103T_NOTE;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.smartnus.testutil.NoteBuilder;


public class NoteListTest {
    private final NoteList noteList = new NoteList();

    @Test
    public void add_nullNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> noteList.add(null));
    }

    @Test
    public void setNote_nullEditedNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> noteList.setNote(CS2103T_NOTE, null));
    }

    @Test
    public void setNote_editedNoteHasSameIdentity_success() {
        noteList.add(CS2103T_NOTE);
        Note editedCs2103t = new NoteBuilder(CS2103T_NOTE).withTags(VALID_NOTE_TAG_1)
                .build();
        noteList.setNote(CS2103T_NOTE, editedCs2103t);
        NoteList expectedNoteList = new NoteList();
        expectedNoteList.add(editedCs2103t);
        assertEquals(expectedNoteList, noteList);
    }

    @Test
    public void setNote_editedNoteHasDifferentIdentity_success() {
        noteList.add(CS2103T_NOTE);
        noteList.setNote(CS2103T_NOTE, CS2100_NOTE);
        NoteList expectedNoteList = new NoteList();
        expectedNoteList.add(CS2100_NOTE);
        assertEquals(expectedNoteList, noteList);
    }

    @Test
    public void remove_nullNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> noteList.remove(null));
    }

    @Test
    public void remove_existingNote_removesNote() {
        noteList.add(CS2100_NOTE);
        noteList.remove(CS2100_NOTE);
        NoteList expectedNoteList = new NoteList();
        assertEquals(expectedNoteList, noteList);
    }

    @Test
    public void setNotes_nullNoteList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> noteList.setNotes((List<Note>) null));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> noteList.asUnmodifiableObservableList().remove(0));
    }
}

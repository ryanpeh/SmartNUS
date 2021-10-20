package seedu.smartnus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smartnus.storage.JsonAdaptedNote.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.smartnus.testutil.Assert.assertThrows;
import static seedu.smartnus.testutil.TypicalNotes.CS2103T_NOTE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.smartnus.commons.exceptions.IllegalValueException;
import seedu.smartnus.model.note.NoteName;

public class JsonAdaptedNoteTest {
    private static final String INVALID_NAME = " ";
    private static final String INVALID_TAG = "#mod";

    private static final String VALID_NAME = CS2103T_NOTE.getTitle();
    private static final List<JsonAdaptedTag> VALID_TAGS = CS2103T_NOTE.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validNoteDetails_returnsNote() throws Exception {
        JsonAdaptedNote note = new JsonAdaptedNote(CS2103T_NOTE);
        assertEquals(CS2103T_NOTE, note.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedNote note =
                new JsonAdaptedNote(INVALID_NAME, VALID_TAGS);
        String expectedMessage = NoteName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, note::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedNote note = new JsonAdaptedNote(null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, NoteName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, note::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedNote note =
                new JsonAdaptedNote(VALID_NAME, invalidTags);
        assertThrows(IllegalValueException.class, note::toModelType);
    }
}

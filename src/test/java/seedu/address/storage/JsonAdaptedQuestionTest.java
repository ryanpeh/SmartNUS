package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedQuestion.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalQuestions.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.question.Importance;
import seedu.address.model.question.Name;

public class JsonAdaptedQuestionTest {
    private static final String INVALID_IMPORTANCE = "+651234";
    private static final String INVALID_NAME = " ";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_IMPORTANCE = BENSON.getImportance().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validQuestionDetails_returnsQuestion() throws Exception {
        JsonAdaptedQuestion question = new JsonAdaptedQuestion(BENSON);
        assertEquals(BENSON, question.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedQuestion question =
                new JsonAdaptedQuestion(INVALID_NAME, VALID_IMPORTANCE, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, question::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedQuestion question = new JsonAdaptedQuestion(null, VALID_IMPORTANCE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, question::toModelType);
    }

    @Test
    public void toModelType_invalidImportance_throwsIllegalValueException() {
        JsonAdaptedQuestion question =
                new JsonAdaptedQuestion(VALID_NAME, INVALID_IMPORTANCE, VALID_TAGS);
        String expectedMessage = Importance.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, question::toModelType);
    }

    @Test
    public void toModelType_nullImportance_throwsIllegalValueException() {
        JsonAdaptedQuestion question = new JsonAdaptedQuestion(VALID_NAME, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Importance.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, question::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedQuestion question =
                new JsonAdaptedQuestion(VALID_NAME, VALID_IMPORTANCE, invalidTags);
        assertThrows(IllegalValueException.class, question::toModelType);
    }
}

package seedu.smartnus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smartnus.storage.JsonAdaptedQuestion.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.smartnus.testutil.Assert.assertThrows;
import static seedu.smartnus.testutil.TypicalQuestions.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.smartnus.commons.exceptions.IllegalValueException;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Importance;
import seedu.smartnus.model.question.Name;

public class JsonAdaptedQuestionTest {
    private static final String INVALID_IMPORTANCE = "+651234";
    private static final String INVALID_NAME = " ";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_CHOICE = " ";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_IMPORTANCE = BENSON.getImportance().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedChoice> VALID_CHOICES = BENSON.getChoices().stream()
            .map(JsonAdaptedChoice::new)
            .collect(Collectors.toList());
    private static final int DEFAULT_ATTEMPT_COUNT = 0;
    private static final int DEFAULT_CORRECT_COUNT = 0;
    private static final int VALID_QUESTION_TYPE = 0;

    @Test
    public void toModelType_validQuestionDetails_returnsQuestion() throws Exception {
        JsonAdaptedQuestion question = new JsonAdaptedQuestion(BENSON);
        assertEquals(BENSON, question.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedQuestion question =
                new JsonAdaptedQuestion(INVALID_NAME, VALID_IMPORTANCE, VALID_TAGS,
                        VALID_CHOICES, DEFAULT_ATTEMPT_COUNT, DEFAULT_CORRECT_COUNT, VALID_QUESTION_TYPE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, question::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedQuestion question = new JsonAdaptedQuestion(null, VALID_IMPORTANCE, VALID_TAGS,
                VALID_CHOICES, 0, DEFAULT_CORRECT_COUNT, VALID_QUESTION_TYPE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, question::toModelType);
    }

    @Test
    public void toModelType_invalidImportance_throwsIllegalValueException() {
        JsonAdaptedQuestion question =
                new JsonAdaptedQuestion(VALID_NAME, INVALID_IMPORTANCE, VALID_TAGS,
                        VALID_CHOICES, DEFAULT_ATTEMPT_COUNT, DEFAULT_CORRECT_COUNT, VALID_QUESTION_TYPE);
        String expectedMessage = Importance.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, question::toModelType);
    }

    @Test
    public void toModelType_nullImportance_throwsIllegalValueException() {
        JsonAdaptedQuestion question = new JsonAdaptedQuestion(VALID_NAME, null, VALID_TAGS,
                VALID_CHOICES, DEFAULT_ATTEMPT_COUNT, DEFAULT_CORRECT_COUNT, VALID_QUESTION_TYPE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Importance.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, question::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedQuestion question =
                new JsonAdaptedQuestion(VALID_NAME, VALID_IMPORTANCE, invalidTags,
                        VALID_CHOICES, DEFAULT_ATTEMPT_COUNT, DEFAULT_CORRECT_COUNT, VALID_QUESTION_TYPE);
        assertThrows(IllegalValueException.class, question::toModelType);
    }

    @Test
    public void toModelType_invalidChoices_throwsIllegalValueException() {
        List<JsonAdaptedChoice> invalidChoices = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> invalidChoices
            .add(new JsonAdaptedChoice(new Choice(INVALID_CHOICE, true))));
    }
}

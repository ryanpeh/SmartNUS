package seedu.smartnus.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.smartnus.testutil.Assert.assertThrows;
import static seedu.smartnus.testutil.TypicalQuestions.ALICE;
import static seedu.smartnus.testutil.TypicalQuestions.getTypicalSmartNus;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.smartnus.model.note.Note;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.question.exceptions.DuplicateQuestionException;
import seedu.smartnus.testutil.QuestionBuilder;

public class SmartNusTest {

    private final SmartNus smartNus = new SmartNus();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), smartNus.getQuestionList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> smartNus.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlySmartNus_replacesData() {
        SmartNus newData = getTypicalSmartNus();
        smartNus.resetData(newData);
        assertEquals(newData, smartNus);
    }

    @Test
    public void resetData_withDuplicateQuestions_throwsDuplicateQuestionException() {
        // Two questions with the same identity fields
        Question editedAlice = new QuestionBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Question> newQuestions = Arrays.asList(ALICE, editedAlice);
        SmartNusStub newData = new SmartNusStub(newQuestions);

        assertThrows(DuplicateQuestionException.class, () -> smartNus.resetData(newData));
    }

    @Test
    public void hasQuestion_nullQuestion_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> smartNus.hasQuestion(null));
    }

    @Test
    public void hasQuestion_questionNotInSmartNus_returnsFalse() {
        assertFalse(smartNus.hasQuestion(ALICE));
    }

    @Test
    public void hasQuestion_questionInSmartNus_returnsTrue() {
        smartNus.addQuestion(ALICE);
        assertTrue(smartNus.hasQuestion(ALICE));
        assertNotEquals(null, smartNus.getQuestionsAsList());
        assertNotEquals(0, smartNus.hashCode());
    }

    @Test
    public void hasQuestion_questionWithSameIdentityFieldsInSmartNus_returnsTrue() {
        smartNus.addQuestion(ALICE);
        Question editedAlice = new QuestionBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(smartNus.hasQuestion(editedAlice));
    }

    @Test
    public void getQuestionList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> smartNus.getQuestionList().remove(0));
    }

    /**
     * A stub ReadOnlySmartNus whose questions list can violate interface constraints.
     */
    private static class SmartNusStub implements ReadOnlySmartNus {
        private final ObservableList<Question> questions = FXCollections.observableArrayList();
        private final ObservableList<Note> notes = FXCollections.observableArrayList();

        SmartNusStub(Collection<Question> questions) {
            this.questions.setAll(questions);
        }

        @Override
        public ObservableList<Question> getQuestionList() {
            return questions;
        }

        @Override
        public ObservableList<Note> getNoteList() {
            return notes;
        }
    }

}

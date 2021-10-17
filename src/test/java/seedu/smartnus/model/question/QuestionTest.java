package seedu.smartnus.model.question;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_IMPORTANCE_BOB;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.smartnus.testutil.Assert.assertThrows;
import static seedu.smartnus.testutil.TypicalQuestions.ALICE;
import static seedu.smartnus.testutil.TypicalQuestions.BOB;

import org.junit.jupiter.api.Test;

import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.tag.Tag;
import seedu.smartnus.testutil.QuestionBuilder;

public class QuestionTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Question question = new QuestionBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> question.getTags().remove(0));
    }

    @Test
    public void isSameQuestion() {
        // same object -> returns true
        assertTrue(ALICE.isSameQuestion(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameQuestion(null));

        // same name, all other attributes different -> returns true
        Question editedAlice = new QuestionBuilder(ALICE).withImportance(VALID_IMPORTANCE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameQuestion(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new QuestionBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameQuestion(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Question editedBob = new QuestionBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameQuestion(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new QuestionBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameQuestion(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Question aliceCopy = new QuestionBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));
        // added to test code coverage
        assertFalse(ALICE.equals(new Tag("abc")));

        // different question -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Question editedAlice = new QuestionBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different importance -> returns false
        editedAlice = new QuestionBuilder(ALICE).withImportance(VALID_IMPORTANCE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new QuestionBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void hashCode_test() {
        Question aliceQuestion = new QuestionBuilder(ALICE).build();
        Question aliceCopy = new QuestionBuilder(ALICE).build();

        assertEquals(aliceQuestion.hashCode(), aliceCopy.hashCode());

        Question bobQuestion = new QuestionBuilder(BOB).build();
        assertNotEquals(aliceQuestion.hashCode(), bobQuestion.hashCode());

        // different importance -> returns false
        Question editedAlice = new QuestionBuilder(ALICE).withImportance(VALID_IMPORTANCE_BOB).build();
        assertNotEquals(aliceQuestion.hashCode(), editedAlice.hashCode());

        // different tags -> returns false
        editedAlice = new QuestionBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertNotEquals(aliceQuestion.hashCode(), editedAlice.hashCode());
    }

    @Test
    public void isValidQuestion_lessThanThreeIncorrectOptionsMcq_false() {
        Question invalidMcq = new QuestionBuilder().withChoices(
                new Choice("answer", true), new Choice("option 1", false)
        ).build();
        assertFalse(invalidMcq.isValidQuestion());
    }

    @Test
    public void isValidQuestion_noCorrectOption_false() {
        Question invalidMcq = new QuestionBuilder().withChoices(
                new Choice("option 2", false), new Choice("option 1", false),
                new Choice("option 3", false), new Choice("option 4", false)
        ).build();
        assertFalse(invalidMcq.isValidQuestion());
    }

    @Test
    public void isValidQuestion_validMcq_true() {
        Question validMcq = new QuestionBuilder().withChoices(
                new Choice("option 2", true), new Choice("option 1", false),
                new Choice("option 3", false), new Choice("option 4", false)
        ).build();
        assertTrue(validMcq.isValidQuestion());
    }
}

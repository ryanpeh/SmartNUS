package seedu.address.model.question;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.QuestionBuilder;

public class QuestionTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Question question = new QuestionBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> question.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Question editedAlice = new QuestionBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new QuestionBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Question editedBob = new QuestionBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new QuestionBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
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

        // different phone -> returns false
        editedAlice = new QuestionBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new QuestionBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new QuestionBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
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
        
        // different phone -> returns false
        Question editedAlice = new QuestionBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertNotEquals(aliceQuestion.hashCode(), editedAlice.hashCode());

        // different email -> returns false
        editedAlice = new QuestionBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertNotEquals(aliceQuestion.hashCode(), editedAlice.hashCode());

        // different address -> returns false
        editedAlice = new QuestionBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertNotEquals(aliceQuestion.hashCode(), editedAlice.hashCode());

        // different tags -> returns false
        editedAlice = new QuestionBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertNotEquals(aliceQuestion.hashCode(), editedAlice.hashCode());
    }
}

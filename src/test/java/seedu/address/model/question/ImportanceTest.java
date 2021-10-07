package seedu.address.model.question;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ImportanceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Importance(null));
    }

    @Test
    public void constructor_invalidImportance_throwsIllegalArgumentException() {
        String invalidImportance = "";
        assertThrows(IllegalArgumentException.class, () -> new Importance(invalidImportance));
    }

    @Test
    public void isValidImportance() {
        // null importance number
        assertThrows(NullPointerException.class, () -> Importance.isValidImportance(null));

        // invalid importance numbers
        assertFalse(Importance.isValidImportance("")); // empty string
        assertFalse(Importance.isValidImportance(" ")); // spaces only
        assertFalse(Importance.isValidImportance("91")); // more than 1 digit
        assertFalse(Importance.isValidImportance("importance")); // non-numeric
        assertFalse(Importance.isValidImportance("1p")); // alphabets within digits
        assertFalse(Importance.isValidImportance("p1")); // alphabets within digits
        assertFalse(Importance.isValidImportance("2 2")); // spaces within digits
        assertFalse(Importance.isValidImportance("0")); // not within range of 1-3
        assertFalse(Importance.isValidImportance("4")); // not within range of 1-3

        // valid importance numbers
        assertTrue(Importance.isValidImportance("1")); // exactly 1 number
        assertTrue(Importance.isValidImportance("2")); // exactly 1 number
        assertTrue(Importance.isValidImportance("3")); // exactly 1 number
    }
}

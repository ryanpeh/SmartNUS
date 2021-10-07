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
        assertFalse(Importance.isValidImportance("91")); // less than 3 numbers
        assertFalse(Importance.isValidImportance("importance")); // non-numeric
        assertFalse(Importance.isValidImportance("9011p041")); // alphabets within digits
        assertFalse(Importance.isValidImportance("9312 1534")); // spaces within digits

        // valid importance numbers
        assertTrue(Importance.isValidImportance("911")); // exactly 3 numbers
        assertTrue(Importance.isValidImportance("93121534"));
        assertTrue(Importance.isValidImportance("124293842033123")); // long importance numbers
    }
}

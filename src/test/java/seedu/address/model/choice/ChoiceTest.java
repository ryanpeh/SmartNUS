package seedu.address.model.choice;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ChoiceTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Choice(null, true));
    }

    @Test
    public void constructor_invalidChoiceTitle_throwsIllegalArgumentException() {
        String invalidChoiceName = "";
        assertThrows(IllegalArgumentException.class, () -> new Choice(invalidChoiceName, true));
    }

    @Test
    public void isValidChoiceTitle_nullTitle_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Choice.isValidChoiceTitle(null));
    }

    @Test
    public void isValidChoiceTitle_emptyStringTitle_false() {
        // Choice title containing only spaces
        assertFalse(Choice.isValidChoiceTitle(""));
        assertFalse(Choice.isValidChoiceTitle("  "));
    }

    @Test
    public void isValidChoiceTitle_validTitle_true() {
        assertTrue(Choice.isValidChoiceTitle("option 1"));
        assertTrue(Choice.isValidChoiceTitle("10"));
        assertTrue(Choice.isValidChoiceTitle("901p#__"));
    }

    @Test
    public void equals() {
        Choice choiceACorrect = new Choice("choice A", true);
        Choice choiceAWrong = new Choice("choice A", false);
        Choice choiceBCorrect = new Choice("choice B", true);

        // different isCorrect values
        assertFalse(choiceACorrect.equals(choiceAWrong));

        // different titles
        assertFalse(choiceBCorrect.equals(choiceACorrect));

        assertTrue(choiceACorrect.equals(new Choice("choice A", true)));
    }
}

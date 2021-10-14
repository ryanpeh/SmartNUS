package seedu.smartnus.model.util;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartnus.model.util.SampleDataUtil.getSampleSmartNus;

import org.junit.jupiter.api.Test;

import seedu.smartnus.model.ReadOnlyAddressBook;
import seedu.smartnus.model.question.Question;


public class SampleDataUtilTest {
    @Test
    public void getSampleAddressBookTest() {
        ReadOnlyAddressBook sampleAddressBook = getSampleSmartNus();
        for (Question question : sampleAddressBook.getQuestionList()) {
            assertTrue(question.isValidQuestion());
        }
    }
}

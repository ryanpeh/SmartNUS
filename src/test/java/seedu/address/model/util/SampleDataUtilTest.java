package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.util.SampleDataUtil.getSampleAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.question.Question;


public class SampleDataUtilTest {
    @Test
    public void getSampleAddressBookTest() {
        ReadOnlyAddressBook sampleAddressBook = getSampleAddressBook();
        for (Question question : sampleAddressBook.getQuestionList()) {
            assertTrue(question.isValidQuestion());
        }
    }
}

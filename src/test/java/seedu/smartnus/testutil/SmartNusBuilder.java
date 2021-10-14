package seedu.smartnus.testutil;

import seedu.smartnus.model.SmartNus;
import seedu.smartnus.model.question.Question;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code SmartNus ab = new AddressBookBuilder().withQuestion("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private SmartNus addressBook;

    public AddressBookBuilder() {
        addressBook = new SmartNus();
    }

    public AddressBookBuilder(SmartNus addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Question} to the {@code SmartNus} that we are building.
     */
    public AddressBookBuilder withQuestion(Question question) {
        addressBook.addQuestion(question);
        return this;
    }

    public SmartNus build() {
        return addressBook;
    }
}

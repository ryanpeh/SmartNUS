package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.question.Question;
import seedu.address.model.question.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the question list with {@code questions}.
     * {@code questions} must not contain duplicate questions.
     */
    public void setPersons(List<Question> questions) {
        this.persons.setPersons(questions);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// question-level operations

    /**
     * Returns true if a question with the same identity as {@code question} exists in the address book.
     */
    public boolean hasPerson(Question question) {
        requireNonNull(question);
        return persons.contains(question);
    }

    /**
     * Adds a question to the address book.
     * The question must not already exist in the address book.
     */
    public void addPerson(Question p) {
        persons.add(p);
    }

    /**
     * Replaces the given question {@code target} in the list with {@code editedQuestion}.
     * {@code target} must exist in the address book.
     * The question identity of {@code editedQuestion} must not be the same as another existing question
     * in the address book.
     */
    public void setPerson(Question target, Question editedQuestion) {
        requireNonNull(editedQuestion);

        persons.setPerson(target, editedQuestion);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Question key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Question> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}

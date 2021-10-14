package seedu.smartnus.model;

import javafx.collections.ObservableList;
import seedu.smartnus.model.question.Question;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the questions list.
     * This list will not contain any duplicate questions.
     */
    ObservableList<Question> getQuestionList();

}

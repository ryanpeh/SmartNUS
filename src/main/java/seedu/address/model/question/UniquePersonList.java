package seedu.address.model.question;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.question.exceptions.DuplicatePersonException;
import seedu.address.model.question.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A question is considered unique by comparing using {@code Question#isSamePerson(Question)}.
 * As such, adding and updating of persons uses Question#isSamePerson(Question) for equality
 * to ensure that the question being added or updated is unique in terms of identity in
 * the UniquePersonList. However, the removal of a question uses Question#equals(Object) to
 * ensure that the question with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Question#isSamePerson(Question)
 */
public class UniquePersonList implements Iterable<Question> {

    private final ObservableList<Question> internalList = FXCollections.observableArrayList();
    private final ObservableList<Question> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent question as the given argument.
     */
    public boolean contains(Question toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a question to the list.
     * The question must not already exist in the list.
     */
    public void add(Question toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the question {@code target} in the list with {@code editedQuestion}.
     * {@code target} must exist in the list.
     * The question identity of {@code editedQuestion} must not be the same as another existing question in the list.
     */
    public void setPerson(Question target, Question editedQuestion) {
        requireAllNonNull(target, editedQuestion);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedQuestion) && contains(editedQuestion)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedQuestion);
    }

    /**
     * Removes the equivalent question from the list.
     * The question must exist in the list.
     */
    public void remove(Question toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setPersons(UniquePersonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code questions}.
     * {@code questions} must not contain duplicate questions.
     */
    public void setPersons(List<Question> questions) {
        requireAllNonNull(questions);
        if (!personsAreUnique(questions)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(questions);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Question> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Question> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePersonList // instanceof handles nulls
                        && internalList.equals(((UniquePersonList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code questions} contains only unique questions.
     */
    private boolean personsAreUnique(List<Question> questions) {
        for (int i = 0; i < questions.size() - 1; i++) {
            for (int j = i + 1; j < questions.size(); j++) {
                if (questions.get(i).isSamePerson(questions.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

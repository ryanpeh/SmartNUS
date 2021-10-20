package seedu.smartnus.model.question.predicate;

import java.util.function.Predicate;

import seedu.smartnus.commons.core.index.Index;
import seedu.smartnus.model.question.Question;

/**
 * Predicate to get a specific question from the question list based on the index.
 */
public class ShowQuestionIndexPredicate implements Predicate<Question> {

    private int currentIndex;
    private Index targetIndex;

    /**
     * Constructor for the predicate.
     * @param targetIndex The target index of the list.
     */
    public ShowQuestionIndexPredicate(Index targetIndex) {
        this.currentIndex = 0;
        this.targetIndex = targetIndex;
    }

    @Override
    public boolean test(Question question) {
        if (currentIndex == targetIndex.getZeroBased()) {
            currentIndex++;
            return true;
        } else {
            currentIndex++;
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowQuestionIndexPredicate // instanceof handles nulls
                && targetIndex.equals(((ShowQuestionIndexPredicate) other).targetIndex)); // state check
    }
}

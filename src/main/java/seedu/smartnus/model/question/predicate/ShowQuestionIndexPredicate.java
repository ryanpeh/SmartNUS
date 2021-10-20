package seedu.smartnus.model.question.predicate;

import seedu.smartnus.commons.core.index.Index;
import seedu.smartnus.model.question.Question;

import java.util.function.Predicate;

public class ShowQuestionIndexPredicate implements Predicate<Question> {

    private int currentIndex;
    private Index targetIndex;

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
}

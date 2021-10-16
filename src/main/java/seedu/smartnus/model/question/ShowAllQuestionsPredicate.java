package seedu.smartnus.model.question;

import java.util.function.Predicate;


/**
 * Tests and returns true for any {@code Question}.
 */
public class ShowAllQuestionsPredicate implements Predicate<Question> {

    public ShowAllQuestionsPredicate() {
    }

    @Override
    public boolean test(Question question) {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof ShowAllQuestionsPredicate; // instanceof handles nulls
    }

}

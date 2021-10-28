package seedu.smartnus.logic.commands;

import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.question.predicates.ShowAllQuestionsPredicate;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * Contains utility methods used for Commands.
 */
public class CommandUtil {
    /**
     * Returns a single predicate that represents a logical AND of all {@code predicates}.
     *
     * @return A single predicate that represents a logical AND of all Question predicates in {@code predicates}.
     */
    public static Predicate<Question> combinePredicates(Collection<Predicate<Question>> predicates) {
        Predicate<Question> combinedPredicates = new ShowAllQuestionsPredicate();
        for (Predicate<Question> predicate : predicates) {
            combinedPredicates = combinedPredicates.and(predicate);
        }
        return combinedPredicates;
    }
}

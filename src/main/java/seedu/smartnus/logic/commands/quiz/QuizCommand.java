package seedu.smartnus.logic.commands.quiz;


import static java.util.Objects.requireNonNull;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.function.Predicate;

import seedu.smartnus.logic.commands.Command;
import seedu.smartnus.logic.commands.CommandResult;
import seedu.smartnus.logic.commands.exceptions.CommandException;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.question.ShowAllQuestionsPredicate;

/**
 * Starts a quiz
 */
public class QuizCommand extends Command {

    public static final String COMMAND_WORD = "quiz";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Starts a quiz, takes optional arguments "
            + "specifying the questions to be included in the quiz.\n"
            + "Parameters: " + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAG + "CS2103T " + PREFIX_TAG + "CS2100 ";


    public static final String MESSAGE_SUCCESS = "Quiz started!";

    public static final String MESSAGE_NO_QUESTIONS = "Quiz has no questions!";

    private final ArrayList<Predicate<Question>> predicates = new ArrayList<>();

    public QuizCommand(Predicate<Question> tagPredicate) {
        this.predicates.add(tagPredicate);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // TODO: Update state (model) with Quiz object?
        model.updateFilteredQuizQuestionList(combinePredicates());

        if (model.getFilteredQuizQuestionList().isEmpty()) {
            throw new CommandException(MESSAGE_NO_QUESTIONS);
        }

        // TODO: Find some other way of doing this? Making the constructor so long isn't that good as well
        //       Maybe explore overloading or something I'm not sure
        return new CommandResult(MESSAGE_SUCCESS, false, false, true);
    }

    /**
     * Returns a single predicate that represents a logical AND of all predicates.
     *
     * @return A single predicate that represents a logical AND of all predicates.
     */
    private Predicate<Question> combinePredicates() {
        Predicate<Question> combinedPredicates = new ShowAllQuestionsPredicate();
        for (Predicate<Question> predicate : predicates) {
            combinedPredicates = combinedPredicates.and(predicate);
        }
        return combinedPredicates;
    }

    @Override
    public boolean equals(Object other) {
        // TODO: In future, check if the attributes (if any) for the QuizCommand are the same
        return other == this // short circuit if same object
                || (other instanceof QuizCommand) // instanceof handles nulls
                && predicates.equals(((QuizCommand) other).predicates);
    }

}

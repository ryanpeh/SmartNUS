package seedu.smartnus.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_IMPORTANCE;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import seedu.smartnus.commons.core.Messages;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.question.predicates.ShowAllQuestionsPredicate;

/**
 * Finds and lists all questions in SmartNUS whose name contains any of the argument keywords,
 * any of the specified tags and has the specified importance.
 * User can choose to specify keywords, tag names, and/or importance, but at least one field must be specified.
 * Keyword and tag name matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all questions whose names contain any of"
            + " the specified keywords (case-insensitive), and any of the specified tags (case-insensitive)"
            + " and displays them as a list with index numbers.\n"
            + "Parameters: [KEYWORD]..."
            + " [" + PREFIX_TAG + "TAG]..."
            + " [" + PREFIX_IMPORTANCE + "IMPORTANCE]\n"
            + "Example: " + COMMAND_WORD + " definition algorithm "
            + PREFIX_TAG + "CS2103T " + PREFIX_TAG + "CS2100 "
            + PREFIX_IMPORTANCE + "1\n";

    public static final String MESSAGE_NO_FIELDS_SPECIFIED = "You must specify at least one keyword or parameter"
            + " to filter questions by.";

    private final Set<Predicate<Question>> predicateSet = new HashSet<>();
    private final Predicate<Question> combinedPredicate;


    /**
     * Creates a FindCommand that contains an {@code ArrayList} of {@code Predicate<Question>} {@code predicates}.
     *
     * @param predicates Predicates to be stored in the FindCommand that will be used to filter Questions.
     */
    public FindCommand(ArrayList<Predicate<Question>> predicates) {
        requireNonNull(predicates);
        predicateSet.addAll(predicates);
        this.combinedPredicate = CommandUtil.combinePredicates(predicateSet);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredQuestionList(combinedPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_QUESTIONS_LISTED_OVERVIEW, model.getFilteredQuestionList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicateSet.equals(((FindCommand) other).predicateSet)); // state check
    }
}

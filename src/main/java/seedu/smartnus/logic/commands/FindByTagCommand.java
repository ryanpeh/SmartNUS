package seedu.smartnus.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.smartnus.commons.core.Messages;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.question.predicate.TagsContainKeywordsPredicate;

/**
 * Finds and lists all questions in smartNUS whose tags contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindByTagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all questions whose tags contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " cs2100 cs2103t ma1521";

    private final TagsContainKeywordsPredicate predicate;

    public FindByTagCommand(TagsContainKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredQuestionList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_QUESTIONS_LISTED_OVERVIEW, model.getFilteredQuestionList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindByTagCommand // instanceof handles nulls
                && predicate.equals(((FindByTagCommand) other).predicate)); // state check
    }
}

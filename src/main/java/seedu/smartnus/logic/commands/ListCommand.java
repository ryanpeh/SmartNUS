package seedu.smartnus.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.smartnus.model.Model.PREDICATE_SHOW_ALL_QUESTIONS;

import seedu.smartnus.model.Model;

/**
 * Lists all questions in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all questions";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredQuestionList(PREDICATE_SHOW_ALL_QUESTIONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

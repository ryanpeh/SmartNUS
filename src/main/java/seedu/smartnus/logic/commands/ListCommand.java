package seedu.smartnus.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.smartnus.model.Model.PREDICATE_SHOW_ALL_NOTES;
import static seedu.smartnus.model.Model.PREDICATE_SHOW_ALL_QUESTIONS;

import seedu.smartnus.model.Model;

/**
 * Lists all questions in SmartNus to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS_QUESTIONS = "Listed all questions";
    public static final String MESSAGE_SUCCESS_NOTES = "Listed all notes";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": lists the contents of SmartNUS. "
            + "Parameters: "
            + PREFIX_NOTE + ": list notes or "
            + PREFIX_QUESTION + ": list questions "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NOTE;

    private static boolean displayQuestions = true;

    /**
     * Toggles displayQuestions field based on user input.
     * @param showQuestions decides whether questions are to be displayed or notes.
     */
    public static void listQuestions(boolean showQuestions) {
        displayQuestions = showQuestions;
    }

    /**
     * returns the boolean determining what to list.
     * @return displayQuestions
     */
    public static boolean isDisplayQuestions() {
        return displayQuestions;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        String successMessage;
        if (displayQuestions) {
            model.updateFilteredQuestionList(PREDICATE_SHOW_ALL_QUESTIONS);
            successMessage = MESSAGE_SUCCESS_QUESTIONS;
        } else {
            model.updateFilteredNoteList(PREDICATE_SHOW_ALL_NOTES);
            successMessage = MESSAGE_SUCCESS_NOTES;
        }
        return new CommandResult(successMessage, false, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListCommand); // instanceof handles nulls
    }
}

package seedu.smartnus.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.smartnus.commons.core.Messages;
import seedu.smartnus.commons.core.index.Index;
import seedu.smartnus.logic.commands.exceptions.CommandException;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.note.Note;
import seedu.smartnus.model.question.Question;

/**
 * Deletes a question identified using it's displayed index from SmartNus.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String QUESTION_KEYWORD = "question";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the question identified by the index number used in the displayed question list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_QUESTION_SUCCESS = "Deleted Question: %1$s";
    public static final String MESSAGE_DELETE_NOTE_SUCCESS = "Deleted Note: %1$s";

    private final Index targetIndex;
    private final boolean deleteFromList;

    /**
     * Instantiates a new DeleteCommand object.
     * @param deleteItem question or note to be deleted.
     * @param targetIndex index of the item to be deleted.
     */
    public DeleteCommand(String deleteItem, Index targetIndex) {
        deleteFromList = deleteItem.equals(QUESTION_KEYWORD);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (deleteFromList) {
            List<Question> lastShownList = model.getFilteredQuestionList();

            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_QUESTION_DISPLAYED_INDEX);
            }

            Question questionToDelete = lastShownList.get(targetIndex.getZeroBased());
            model.deleteQuestion(questionToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_QUESTION_SUCCESS, questionToDelete));
        } else {
            List<Note> lastShownList = model.getFilteredNoteList();

            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
            }

            Note noteToDelete = lastShownList.get(targetIndex.getZeroBased());
            model.deleteNote(noteToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_NOTE_SUCCESS, noteToDelete));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && deleteFromList == ((DeleteCommand) other).deleteFromList // state check
                && targetIndex.equals(((DeleteCommand) other).targetIndex));
    }
}

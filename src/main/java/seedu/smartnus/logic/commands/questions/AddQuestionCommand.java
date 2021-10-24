package seedu.smartnus.logic.commands.questions;

import static java.util.Objects.requireNonNull;
import static seedu.smartnus.logic.commands.AddCommand.MESSAGE_DUPLICATE_QUESTION;

import seedu.smartnus.logic.commands.Command;
import seedu.smartnus.logic.commands.CommandResult;
import seedu.smartnus.logic.commands.exceptions.CommandException;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.question.Question;

public class AddQuestionCommand extends Command {

    public static final String MESSAGE_SUCCESS = "New question added: %s";
    private final Question toAdd;

    /**
     * @param question The question to add to SmartNus
     */
    public AddQuestionCommand(Question question) {
        requireNonNull(question);
        this.toAdd = question;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasQuestion(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_QUESTION);
        }

        model.addQuestion(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddQuestionCommand // instanceof handles nulls
                && toAdd.equals(((AddQuestionCommand) other).toAdd)); // check if the question to add are the same
    }

}

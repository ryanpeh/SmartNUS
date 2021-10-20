package seedu.smartnus.logic.commands.questions;

import seedu.smartnus.model.question.Question;

import static seedu.smartnus.logic.parser.CliSyntax.*;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_IMPORTANCE;

public class AddTfCommand extends AddQuestionCommand {
    public static final String COMMAND_WORD = "tf";
    public static final String MESSAGE_SUCCESS = "New question added: %s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a true/false question to SmartNUS. "
            + "Parameters: "
            + PREFIX_QUESTION + "QUESTION "
            + PREFIX_OPTION + "OPTION "
            + PREFIX_OPTION + "OPTION "
            + PREFIX_OPTION + "OPTION "
            + PREFIX_ANSWER + "ANSWER "
            + PREFIX_IMPORTANCE + "IMPORTANCE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_QUESTION + "What is 1+1? "
            + PREFIX_OPTION + "3 "
            + PREFIX_OPTION + "4 "
            + PREFIX_OPTION + "5 "
            + PREFIX_ANSWER + "2 "
            + PREFIX_IMPORTANCE + "1 ";

    public AddTfCommand(Question question) {
        super(question);
    }
}

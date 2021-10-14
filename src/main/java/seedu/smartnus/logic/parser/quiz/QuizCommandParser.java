package seedu.smartnus.logic.parser.quiz;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.smartnus.logic.commands.quiz.QuizCommand;
import seedu.smartnus.logic.parser.ArgumentMultimap;
import seedu.smartnus.logic.parser.ArgumentTokenizer;
import seedu.smartnus.logic.parser.Parser;
import seedu.smartnus.logic.parser.exceptions.ParseException;

public class QuizCommandParser implements Parser<QuizCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the QuizCommand
     * and returns a QuizCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public QuizCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args);
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, QuizCommand.MESSAGE_USAGE));
        }
        return new QuizCommand();
    }
}

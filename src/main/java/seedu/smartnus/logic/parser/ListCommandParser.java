package seedu.smartnus.logic.parser;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.smartnus.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.smartnus.logic.commands.ListCommand;
import seedu.smartnus.logic.parser.exceptions.ParseException;

public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NOTE, PREFIX_QUESTION);

        // if prefix question is empty, true.
        if (!arePrefixesPresent(argMultimap, PREFIX_QUESTION)) {
            // if prefix note is empty, true.
            if (!arePrefixesPresent(argMultimap, PREFIX_NOTE)) {
                // both cannot be empty.l
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
            } else {
                // question is empty, note is not empty
                ListCommand.setDisplayQuestions(false);
            }
        } else if (!arePrefixesPresent(argMultimap, PREFIX_NOTE)) {
            if (!arePrefixesPresent(argMultimap, PREFIX_QUESTION)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
            } else {
                ListCommand.setDisplayQuestions(true);
            }
        }
        return new ListCommand();
    }
}

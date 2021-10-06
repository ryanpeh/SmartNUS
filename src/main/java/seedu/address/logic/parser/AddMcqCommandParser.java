package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.address.logic.commands.AddMcqCommand;
import seedu.address.logic.parser.exceptions.ParseException;



public class AddMcqCommandParser implements Parser<AddMcqCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddMcqCommand
     * and returns an AddMcqCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddMcqCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_QUESTION, PREFIX_OPTION, PREFIX_ANSWER);

        if (!arePrefixesPresent(argMultimap, PREFIX_QUESTION, PREFIX_OPTION, PREFIX_ANSWER)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMcqCommand.MESSAGE_USAGE));
        }

        String question = ParserUtil.parseQuestion(argMultimap.getValue(PREFIX_QUESTION).get());
        String answer = ParserUtil.parseAnswer(argMultimap.getValue(PREFIX_ANSWER).get());
        String[] options = ParserUtil.parseOptions(argMultimap.getAllValues(PREFIX_OPTION));

        return new AddMcqCommand(question, options, answer);
    }

}

package seedu.smartnus.logic.parser;

import seedu.smartnus.logic.commands.questions.AddMcqCommand;
import seedu.smartnus.logic.commands.questions.AddTfCommand;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.*;
import seedu.smartnus.model.tag.Tag;

import java.util.Set;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.logic.parser.CliSyntax.*;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.smartnus.logic.parser.ParserUtil.arePrefixesPresent;

public class AddTfCommandParser implements Parser<AddTfCommand> {

    public static final String ANSWER_TRUE = "T";
    public static final String ANSWER_FALSE = "F";

    /**
     * Parses the given {@code String} of arguments in the context of the AddTfCommand
     * and returns an AddTfCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTfCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_QUESTION,
                        PREFIX_ANSWER, PREFIX_IMPORTANCE, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_QUESTION, PREFIX_ANSWER, PREFIX_IMPORTANCE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTfCommand.MESSAGE_USAGE));
        }

        Name questionName = ParserUtil.parseName(argMultimap.getValue(PREFIX_QUESTION).get());
        Importance importance = ParserUtil.parseImportance(argMultimap.getValue(PREFIX_IMPORTANCE).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Set<Choice> choices = ParserUtil.parseTrueFalseAnswer(argMultimap.getValue(PREFIX_ANSWER).get());

        Question toAdd = new TrueFalseQuestion(questionName, importance, tagList, choices);

        return new AddTfCommand(toAdd);
    }
}

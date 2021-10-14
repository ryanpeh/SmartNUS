package seedu.smartnus.logic.parser;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_IMPORTANCE;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.smartnus.logic.parser.ParserUtil.arePrefixesPresent;

import java.util.HashSet;
import java.util.Set;

import seedu.smartnus.logic.commands.AddCommand;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Importance;
import seedu.smartnus.model.question.MultipleChoiceQuestion;
import seedu.smartnus.model.question.Name;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_IMPORTANCE, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_IMPORTANCE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Importance importance = ParserUtil.parseImportance(argMultimap.getValue(PREFIX_IMPORTANCE).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        // TODO: implement parsing for choices
        Set<Choice> choiceList = new HashSet<>();
        choiceList.add(new Choice("Empty Choice", true));
        // TODO: check if AddCommand will be used for different types of Questions
        Question question = new MultipleChoiceQuestion(name, importance, tagList, choiceList);

        return new AddCommand(question);
    }


}

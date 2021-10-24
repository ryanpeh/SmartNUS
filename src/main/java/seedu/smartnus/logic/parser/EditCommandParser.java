package seedu.smartnus.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_IMPORTANCE;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.smartnus.commons.core.index.Index;
import seedu.smartnus.logic.commands.EditCommand;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_QUESTION, PREFIX_IMPORTANCE, PREFIX_TAG, PREFIX_OPTION,
                        PREFIX_ANSWER);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditCommand.EditQuestionDescriptor editQuestionDescriptor = new EditCommand.EditQuestionDescriptor();
        if (argMultimap.getValue(PREFIX_QUESTION).isPresent()) {
            editQuestionDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_QUESTION).get()));
        }
        if (argMultimap.getValue(PREFIX_IMPORTANCE).isPresent()) {
            editQuestionDescriptor.setImportance(ParserUtil.parseImportance(
                    argMultimap.getValue(PREFIX_IMPORTANCE).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editQuestionDescriptor::setTags);
        
        if (argMultimap.getValue(PREFIX_ANSWER).isPresent()) {
            Choice answer = ParserUtil.parseAnswerForEdit(argMultimap.getValue(PREFIX_ANSWER).get());
            editQuestionDescriptor.setAnswer(answer);
            // TF choices require additional parsing as user input (e.g. T) is not the same as choice title (e.g. True)
            // this is necessary to ensure decoupling of parser and logic/model components
            Set<Choice> tfChoices = ParserUtil.parseTrueFalseAnswerForEdit(answer.getTitle());
            editQuestionDescriptor.setTfChoices(tfChoices);
        }
        
        Set<Choice> wrongChoices = ParserUtil.parseWrongChoicesForEdit(argMultimap.getAllValues(PREFIX_OPTION));
        if (!wrongChoices.isEmpty()) {
            editQuestionDescriptor.setWrongChoices(wrongChoices);
        }
        
        if (!editQuestionDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }
        
        return new EditCommand(index, editQuestionDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}

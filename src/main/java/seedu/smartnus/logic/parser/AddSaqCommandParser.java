package seedu.smartnus.logic.parser;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_IMPORTANCE;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_KEYWORD;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.smartnus.logic.parser.ParserUtil.arePrefixesPresent;
import static seedu.smartnus.model.choice.Choice.MESSAGE_KEYWORD_CONSTRAINTS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.smartnus.logic.commands.questions.AddSaqCommand;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Importance;
import seedu.smartnus.model.question.Name;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.question.ShortAnswerQuestion;
import seedu.smartnus.model.tag.Tag;


public class AddSaqCommandParser implements Parser<AddSaqCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddSaqCommand
     * and returns an AddSaqCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddSaqCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_QUESTION,
                        PREFIX_ANSWER, PREFIX_IMPORTANCE, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_QUESTION, PREFIX_ANSWER, PREFIX_IMPORTANCE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSaqCommand.MESSAGE_USAGE));
        }

        Name questionName = ParserUtil.parseName(argMultimap.getValue(PREFIX_QUESTION).get());
        Importance importance = ParserUtil.parseImportance(argMultimap.getValue(PREFIX_IMPORTANCE).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Set<Choice> choices = new HashSet<>();
        List<String> answerStrings = argMultimap.getAllValues(PREFIX_ANSWER);
        for (String answerString : answerStrings) {
            // ArgumentTokenizer expects there to be a space " " before the prefix
            ArgumentMultimap keywordsMultimap = ArgumentTokenizer.tokenize(" " + answerString, PREFIX_KEYWORD);
            if (!arePrefixesPresent(keywordsMultimap, PREFIX_KEYWORD)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddSaqCommand.MESSAGE_NO_KEYWORDS));
            }
            addAnswerToChoices(answerString, keywordsMultimap, choices);
        }

        Question toAdd = new ShortAnswerQuestion(questionName, importance, tagList, choices);

        return new AddSaqCommand(toAdd);
    }
    
    private void addAnswerToChoices(String answerString, ArgumentMultimap keywordsMultimap, Set<Choice> choices) 
            throws ParseException {
        String answerTitleWithoutPrefix = answerString.replaceAll(PREFIX_KEYWORD.toString(), "");
        Set<String> parsedKeywords = new HashSet<>();
        List<String> keywordsList = keywordsMultimap.getAllValues(PREFIX_KEYWORD);
        checkEmptyKeywords(keywordsList);
        
        for (String keywords : keywordsList) {
            String[] singleWords = keywords.split("\\W+");
            // only the first non-empty word specified after prefix is a keyword
            for (String word : singleWords) {
                if (word.isBlank()) {
                    continue;
                }
                parsedKeywords.add(word);
                break;
            }
        }
        choices.add(new Choice(answerTitleWithoutPrefix, true, parsedKeywords));
    }

    private void checkEmptyKeywords(List<String> keywords) throws ParseException {
        for (String word : keywords) {
            if (word.isBlank()) {
                throw new ParseException(MESSAGE_KEYWORD_CONSTRAINTS);
            }
        }
    }
}

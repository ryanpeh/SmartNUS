package seedu.smartnus.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.smartnus.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.smartnus.logic.commands.ThemeCommand.DARK_KEYWORD;
import static seedu.smartnus.logic.commands.ThemeCommand.LIGHT_KEYWORD;
import static seedu.smartnus.model.question.MultipleChoiceQuestion.NUMBER_OF_INCORRECT_CHOICES;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import seedu.smartnus.commons.core.index.Index;
import seedu.smartnus.commons.util.StringUtil;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Importance;
import seedu.smartnus.model.question.MultipleChoiceQuestion;
import seedu.smartnus.model.question.Name;
import seedu.smartnus.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }


    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String importance} into a {@code Importance}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code importance} is invalid.
     */
    public static Importance parseImportance(String importance) throws ParseException {
        requireNonNull(importance);
        String trimmedImportance = importance.trim();
        if (!Importance.isValidImportance(trimmedImportance)) {
            throw new ParseException(Importance.MESSAGE_CONSTRAINTS);
        }
        return new Importance(trimmedImportance);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses {@code String choice} into a {@code Choice choice}.
     */
    public static Choice parseChoice(String choice, boolean isCorrect) throws ParseException {
        requireNonNull(choice);
        String trimmedChoice = choice.trim();
        if (!Choice.isValidChoiceTitle(trimmedChoice)) {
            throw new ParseException(Choice.MESSAGE_CONSTRAINTS);
        }
        return new Choice(trimmedChoice, isCorrect);
    }

    /**
     * Parses {@code List<String> choices, String answer} into a {@code Set<Choice> choice}.
     */
    public static Set<Choice> parseChoices(List<String> choices, String answer) throws ParseException {
        requireAllNonNull(choices, answer);
        if (choices.size() != NUMBER_OF_INCORRECT_CHOICES) {
            throw new ParseException(MultipleChoiceQuestion.MESSAGE_INCORRECT_NUMBER_OF_CHOICES);
        }
        Set<Choice> choiceSet = new HashSet<>();
        for (int i = 0; i < NUMBER_OF_INCORRECT_CHOICES; i++) {
            String choice = choices.get(i);
            if (choice.equals(answer)) {
                throw new ParseException(MultipleChoiceQuestion.MESSAGE_DUPLICATE_CHOICES);
            }
            choiceSet.add(parseChoice(choice, false));
        }
        if (choiceSet.size() != NUMBER_OF_INCORRECT_CHOICES) {
            throw new ParseException(MultipleChoiceQuestion.MESSAGE_DUPLICATE_CHOICES);
        }
        choiceSet.add(parseChoice(answer, true));
        return choiceSet;
    }

    public static Set<Choice> parseWrongChoicesForEdit(List<String> choices) throws ParseException {
        requireAllNonNull(choices);
        Set<Choice> choiceSet = new HashSet<>();
        for (String choice : choices) {
            choiceSet.add(parseChoice(choice, false));
        }
        if (choiceSet.size() != choices.size()) {
            throw new ParseException(MultipleChoiceQuestion.MESSAGE_DUPLICATE_CHOICES);
        }
        return choiceSet;
    }
    
    public static Choice parseAnswerForEdit(String answer) throws ParseException {
        requireNonNull(answer);
        return parseChoice(answer, true);
    }

    /**
     * Returns True if the given theme is valid.
     * @param theme The given theme.
     * @return True if theme is valid, false otherwise.
     */
    public static boolean isValidTheme(String theme) {
        return theme.trim().equals(LIGHT_KEYWORD) || theme.trim().equals(DARK_KEYWORD);
    }
}

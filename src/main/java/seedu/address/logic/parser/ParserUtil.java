package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.question.Address;
import seedu.address.model.question.Email;
import seedu.address.model.question.Name;
import seedu.address.model.question.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    // TODO: Move these messages to their classes once created
    public static final String MESSAGE_INVALID_QUESTION = "Question cannot be blank.";
    public static final String MESSAGE_INVALID_ANSWER = "Answer cannot be blank.";
    public static final String MESSAGE_INVALID_OPTION = "Option cannot be blank.";
    public static final String MESSAGE_INSUFFICIENT_OPTIONS = "Must have 3 options!";

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
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
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
     * Parses {@code String question} into a {@code String}.
     * TODO: Change this to return Question Object instead
     */
    public static String parseQuestion(String question) throws ParseException {
        requireNonNull(question);
        String trimmedQuestion = question.trim();
        // TODO: Refactor this when the Question class is created:
        //  Parse exception string should be in Question class, as well as the validation logic
        //  (predicate in if statement)
        if (trimmedQuestion.equals("")) {
            throw new ParseException(MESSAGE_INVALID_QUESTION);
        }
        return trimmedQuestion;
    }

    /**
     * Parses {@code String answer} into a {@code String}.
     * TODO: Change this to return Answer/Option Object instead
     */
    public static String parseAnswer(String answer) throws ParseException {
        requireNonNull(answer);
        String trimmedAnswer = answer.trim();
        // TODO: Refactor this when the Answer/Options class is created:
        //  Parse exception string should be in Answer/Options class, as well as the validation logic
        //  (predicate in if statement)
        if (trimmedAnswer.equals("")) {
            throw new ParseException(MESSAGE_INVALID_ANSWER);
        }
        return trimmedAnswer;
    }

    /**
     * Parses {@code String options} into a {@code String}.
     * TODO: Change this to return Option Object instead
     */
    public static String parseOption(String option) throws ParseException {
        requireNonNull(option);
        String trimmedOption = option.trim();
        // TODO: Refactor this when the Option class is created:
        //  Parse exception string should be in Option class, as well as the validation logic
        //  (predicate in if statement)
        if (trimmedOption.equals("")) {
            throw new ParseException(MESSAGE_INVALID_OPTION);
        }
        return trimmedOption;
    }

    /**
     * Parses {@code List<String> options} into a {@code String[]}.
     * TODO: Change this to return Options Object instead
     */
    public static String[] parseOptions(List<String> options) throws ParseException {
        requireNonNull(options);
        // TODO: Refactor this when the Options class is created:
        //  Parse exception string should be in Options class, as well as the validation logic
        //  (predicate in if statement + cannot have duplicate options)
        if (options.size() < 3) {
            throw new ParseException(MESSAGE_INSUFFICIENT_OPTIONS);
        }
        String[] optionsArr = options.stream().limit(3).toArray(String[]::new);
        for (int i = 0; i < optionsArr.length; i++) {
            optionsArr[i] = parseOption(optionsArr[i]);
        }
        return optionsArr;
    }
}

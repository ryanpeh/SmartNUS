package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.question.Importance;
import seedu.address.model.question.Name;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_IMPORTANCE = "+2";
    private static final String INVALID_NAME = " ";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_QUESTION = " ";
    private static final String INVALID_ANSWER = " ";
    private static final String INVALID_OPTION = " ";

    private static final String VALID_IMPORTANCE = "1";
    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_QUESTION = "What is 1+1?";
    private static final String VALID_ANSWER = "2";
    private static final String VALID_OPTION_1 = "3";
    private static final String VALID_OPTION_2 = "4";
    private static final String VALID_OPTION_3 = "1";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, () ->
                ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName(null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseImportance_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseImportance(null));
    }

    @Test
    public void parseImportance_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseImportance(INVALID_IMPORTANCE));
    }

    @Test
    public void parseImportance_validValueWithoutWhitespace_returnsImportance() throws Exception {
        Importance expectedImportance = new Importance(VALID_IMPORTANCE);
        assertEquals(expectedImportance, ParserUtil.parseImportance(VALID_IMPORTANCE));
    }

    @Test
    public void parseImportance_validValueWithWhitespace_returnsTrimmedImportance() throws Exception {
        String importanceWithWhitespace = WHITESPACE + VALID_IMPORTANCE + WHITESPACE;
        Importance expectedImportance = new Importance(VALID_IMPORTANCE);
        assertEquals(expectedImportance, ParserUtil.parseImportance(importanceWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseQuestion_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseQuestion(null));
    }

    @Test
    public void parseQuestion_validValueWithoutWhitespace_returnsQuestion() throws Exception {
        String actualQuestion = ParserUtil.parseQuestion(VALID_QUESTION);
        assertEquals(actualQuestion, VALID_QUESTION);
    }

    @Test
    public void parseQuestion_validValueWithWhitespace_returnsTrimmedQuestion() throws Exception {
        String actualQuestion = ParserUtil.parseQuestion(WHITESPACE + VALID_QUESTION + WHITESPACE);
        assertEquals(actualQuestion, VALID_QUESTION);
    }

    @Test
    public void parseQuestion_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseQuestion(INVALID_QUESTION));
    }

    @Test
    public void parseAnswer_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAnswer(null));
    }

    @Test
    public void parseAnswer_validValueWithoutWhitespace_returnsAnswer() throws Exception {
        String actualAnswer = ParserUtil.parseAnswer(VALID_ANSWER);
        assertEquals(actualAnswer, VALID_ANSWER);
    }

    @Test
    public void parseAnswer_validValueWithWhitespace_returnsAnswer() throws Exception {
        String actualAnswer = ParserUtil.parseAnswer(WHITESPACE + VALID_ANSWER + WHITESPACE);
        assertEquals(actualAnswer, VALID_ANSWER);
    }

    @Test
    public void parseAnswer_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAnswer(INVALID_ANSWER));
    }

    @Test
    public void parseOption_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseOption(null));
    }

    @Test
    public void parseOption_validValueWithoutWhitespace_returnsOption() throws Exception {
        String actualOption = ParserUtil.parseAnswer(VALID_OPTION_1);
        assertEquals(actualOption, VALID_OPTION_1);
    }


    @Test
    public void parseOption_validValueWithWhitespace_returnsOption() throws Exception {
        String actualOption = ParserUtil.parseOption(WHITESPACE + VALID_OPTION_1 + WHITESPACE);
        assertEquals(actualOption, VALID_OPTION_1);
    }

    @Test
    public void parseOption_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseOption(INVALID_OPTION));
    }

    @Test
    public void parseOptions_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseOptions(null));
    }

    @Test
    public void parseOptions_validValues_returnsOptions() throws Exception {
        String[] actualOptions = ParserUtil.parseOptions(Arrays.asList(VALID_OPTION_1, VALID_OPTION_2, VALID_OPTION_3));
        String[] expectedOptions = new String[]{VALID_OPTION_1, VALID_OPTION_2, VALID_OPTION_3};
        // Temp hacky method to compare if the 2 array contents are the same, once Options object is implemented, this
        // can be removed (currently Options is just a String[])
        assertEquals(Arrays.toString(actualOptions), Arrays.toString(expectedOptions));
    }

    @Test
    public void parseOptions_invalidValues_throwsParseException() {

        // One invalid option
        assertThrows(ParseException.class, () ->
                ParserUtil.parseOptions(Arrays.asList(VALID_OPTION_1, INVALID_OPTION, VALID_OPTION_3)));

        // Not enough valid options
        assertThrows(ParseException.class, () ->
                ParserUtil.parseOptions(Arrays.asList(VALID_OPTION_1, VALID_OPTION_2)));

        // No options
        assertThrows(ParseException.class, () -> ParserUtil.parseOptions(Collections.emptyList()));
    }
}

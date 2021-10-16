package seedu.smartnus.logic.parser;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.logic.commands.CommandTestUtil.IMPORTANCE_DESC_AMY;
import static seedu.smartnus.logic.commands.CommandTestUtil.IMPORTANCE_DESC_BOB;
import static seedu.smartnus.logic.commands.CommandTestUtil.INVALID_IMPORTANCE_DESC;
import static seedu.smartnus.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.smartnus.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.smartnus.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.smartnus.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.smartnus.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.smartnus.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.smartnus.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.smartnus.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_IMPORTANCE_BOB;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.smartnus.testutil.TypicalQuestions.AMY;
import static seedu.smartnus.testutil.TypicalQuestions.BOB;

import org.junit.jupiter.api.Test;

import seedu.smartnus.logic.commands.AddCommand;
import seedu.smartnus.model.question.Importance;
import seedu.smartnus.model.question.Name;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.tag.Tag;
import seedu.smartnus.testutil.QuestionBuilder;

public class AddCommandParserTest {
    private final AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Question expectedQuestion = new QuestionBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + IMPORTANCE_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedQuestion));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + IMPORTANCE_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedQuestion));

        // multiple importance numbers - last importance accepted
        assertParseSuccess(parser, NAME_DESC_BOB + IMPORTANCE_DESC_AMY + IMPORTANCE_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedQuestion));

        // multiple tags - all accepted
        Question expectedQuestionMultipleTags = new QuestionBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + IMPORTANCE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedQuestionMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Question expectedQuestion = new QuestionBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + IMPORTANCE_DESC_AMY,
                new AddCommand(expectedQuestion));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + IMPORTANCE_DESC_BOB, expectedMessage);

        // missing importance prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_IMPORTANCE_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_IMPORTANCE_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + IMPORTANCE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid importance
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_IMPORTANCE_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Importance.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + IMPORTANCE_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + IMPORTANCE_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + IMPORTANCE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}

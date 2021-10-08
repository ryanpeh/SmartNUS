package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ANSWER_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.ANSWER_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.IMPORTANCE_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ANSWER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_IMPORTANCE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_OPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_QUESTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.OPTIONS_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.OPTIONS_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.OPTION_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.OPTION_DESC_3;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.QUESTION_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.QUESTION_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IMPORTANCE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OPTION_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OPTION_3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OPTION_4;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddMcqCommand;
import seedu.address.model.choice.Choice;
import seedu.address.model.question.Importance;
import seedu.address.model.question.MultipleChoiceQuestion;
import seedu.address.model.question.Name;
import seedu.address.model.question.Question;


class AddMcqCommandParserTest {

    private final AddMcqCommandParser parser = new AddMcqCommandParser();

    @Test
    void parse_allFieldsValid_success() {
        // TODO: Use question builder for this instead of generating it here
        List<String> choices = Arrays.asList(VALID_OPTION_1, VALID_OPTION_3, VALID_OPTION_4);
        Set<Choice> expectedChoices = new HashSet<>();
        for (String choice: choices) {
            expectedChoices.add(new Choice(choice, false));
        }
        expectedChoices.add(new Choice(VALID_ANSWER_1, true));

        Importance expectedImportance = new Importance(VALID_IMPORTANCE_1);
        Name expectedName = new Name(VALID_QUESTION_1);

        Question expectedQuestion = new MultipleChoiceQuestion(expectedName, expectedImportance,
                new HashSet<>(), expectedChoices);

        AddMcqCommand expectedCommand = new AddMcqCommand(expectedQuestion);

        // normal command with preamble whitespace
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + QUESTION_DESC_1 + OPTIONS_DESC_1 + ANSWER_DESC_1 + IMPORTANCE_DESC_1,
                expectedCommand);

        // accept only last argument for question
        assertParseSuccess(parser,
                QUESTION_DESC_2 + OPTIONS_DESC_1 + ANSWER_DESC_1 + IMPORTANCE_DESC_1 + QUESTION_DESC_1,
                expectedCommand);

        // accept only last argument for answer
        assertParseSuccess(parser,
                QUESTION_DESC_1 + ANSWER_DESC_2 + OPTIONS_DESC_1 + IMPORTANCE_DESC_1 + ANSWER_DESC_1,
                expectedCommand);

        // accept only first 3 arguments for options
        assertParseSuccess(parser, QUESTION_DESC_1 + ANSWER_DESC_1 + IMPORTANCE_DESC_1 + OPTIONS_DESC_2,
                expectedCommand);
    }

    @Test
    void parse_fieldsOrPrefixMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMcqCommand.MESSAGE_USAGE);
        // answer missing
        assertParseFailure(parser, QUESTION_DESC_1 + OPTIONS_DESC_1 + IMPORTANCE_DESC_1, expectedMessage);
        // options missing
        assertParseFailure(parser, QUESTION_DESC_1 + ANSWER_DESC_1 + IMPORTANCE_DESC_1, expectedMessage);
        // question missing
        assertParseFailure(parser, ANSWER_DESC_1 + OPTIONS_DESC_1 + IMPORTANCE_DESC_1, expectedMessage);
        // importance missing
        assertParseFailure(parser, ANSWER_DESC_1 + OPTIONS_DESC_1 + QUESTION_DESC_1, expectedMessage);
        // answer prefix missing
        assertParseFailure(parser, QUESTION_DESC_1 + OPTIONS_DESC_1 + VALID_ANSWER_1, expectedMessage);
        // options prefix missing
        assertParseFailure(parser,
                QUESTION_DESC_1 + VALID_OPTION_1 + VALID_OPTION_3 + VALID_OPTION_4 + ANSWER_DESC_1,
                expectedMessage);
        // question prefix missing
        assertParseFailure(parser, VALID_QUESTION_1 + OPTIONS_DESC_1 + ANSWER_DESC_1, expectedMessage);
        // importance prefix missing
        assertParseFailure(parser, ANSWER_DESC_1 + OPTIONS_DESC_1 + QUESTION_DESC_1 + VALID_IMPORTANCE_1,
                expectedMessage);
    }

    @Test
    void parse_fieldsInvalid_failure() {
        // TODO: Add tests for duplicate options and answer
        // invalid answer
        assertParseFailure(parser, QUESTION_DESC_1 + OPTIONS_DESC_1 + INVALID_ANSWER_DESC + IMPORTANCE_DESC_1,
                Choice.MESSAGE_CONSTRAINTS);
        // 1 invalid option with 2 valid
        assertParseFailure(parser, QUESTION_DESC_1 + OPTION_DESC_1 + INVALID_OPTION_DESC + OPTIONS_DESC_2
                + ANSWER_DESC_1 + IMPORTANCE_DESC_1, Choice.MESSAGE_CONSTRAINTS);
        // not enough options
        assertParseFailure(parser,
                QUESTION_DESC_1 + OPTION_DESC_1 + OPTION_DESC_3 + ANSWER_DESC_1 + IMPORTANCE_DESC_1,
                MultipleChoiceQuestion.MESSAGE_INSUFFICIENT_CHOICES);
        // invalid question
        assertParseFailure(parser, INVALID_QUESTION_DESC + OPTIONS_DESC_1 + ANSWER_DESC_1 + IMPORTANCE_DESC_1,
                Name.MESSAGE_CONSTRAINTS);
        // invalid importance
        assertParseFailure(parser,
                QUESTION_DESC_1 + OPTIONS_DESC_1 + ANSWER_DESC_1 + INVALID_IMPORTANCE_DESC,
                Importance.MESSAGE_CONSTRAINTS);
    }
}

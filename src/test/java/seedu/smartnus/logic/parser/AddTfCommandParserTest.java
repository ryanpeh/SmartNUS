package seedu.smartnus.logic.parser;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.logic.commands.CommandTestUtil.ANSWER_DESC_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.ANSWER_DESC_2;
import static seedu.smartnus.logic.commands.CommandTestUtil.IMPORTANCE_DESC_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.INVALID_IMPORTANCE_DESC;
import static seedu.smartnus.logic.commands.CommandTestUtil.INVALID_QUESTION_DESC;
import static seedu.smartnus.logic.commands.CommandTestUtil.OPTIONS_DESC_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.smartnus.logic.commands.CommandTestUtil.QUESTION_DESC_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.TRUE_ANSWER_DESC;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_IMPORTANCE_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_QUESTION_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_TRUE_FALSE_ANSWER_1;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.smartnus.model.choice.Choice.FALSE_CHOICE_TITLE;
import static seedu.smartnus.model.choice.Choice.TRUE_CHOICE_TITLE;
import static seedu.smartnus.model.question.TrueFalseQuestion.MESSAGE_ANSWER_INVALID;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.smartnus.logic.commands.questions.AddTfqCommand;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Importance;
import seedu.smartnus.model.question.Name;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.question.TrueFalseQuestion;

class AddTfqCommandParserTest {

    private final AddTfqCommandParser parser = new AddTfqCommandParser();

    @Test
    void parse_allFieldsValid_success() {
        // TODO: Use question builder for this instead of generating it here
        Set<Choice> expectedChoices = new HashSet<>();
        expectedChoices.add(new Choice(TRUE_CHOICE_TITLE, true));
        expectedChoices.add(new Choice(FALSE_CHOICE_TITLE, false));

        Importance expectedImportance = new Importance(VALID_IMPORTANCE_1);
        Name expectedName = new Name(VALID_QUESTION_1);

        Question expectedQuestion = new TrueFalseQuestion(expectedName, expectedImportance,
                new HashSet<>(), expectedChoices);

        AddTfqCommand expectedCommand = new AddTfqCommand(expectedQuestion);

        // normal command with preamble whitespace
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + QUESTION_DESC_1 + TRUE_ANSWER_DESC + IMPORTANCE_DESC_1,
                expectedCommand);

        // accept only last argument for answer
        assertParseSuccess(parser,
                QUESTION_DESC_1 + ANSWER_DESC_2 + OPTIONS_DESC_1 + IMPORTANCE_DESC_1 + TRUE_ANSWER_DESC,
                expectedCommand);
    }

    @Test
    void parse_fieldsOrPrefixMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTfqCommand.MESSAGE_USAGE);
        // answer missing
        assertParseFailure(parser, QUESTION_DESC_1 + IMPORTANCE_DESC_1, expectedMessage);
        // question missing
        assertParseFailure(parser, TRUE_ANSWER_DESC + OPTIONS_DESC_1 + IMPORTANCE_DESC_1, expectedMessage);
        // importance missing
        assertParseFailure(parser, TRUE_ANSWER_DESC + OPTIONS_DESC_1 + QUESTION_DESC_1, expectedMessage);
        // answer prefix missing
        assertParseFailure(parser, QUESTION_DESC_1 + OPTIONS_DESC_1 + VALID_TRUE_FALSE_ANSWER_1,
                expectedMessage);
        // question prefix missing
        assertParseFailure(parser, VALID_QUESTION_1 + OPTIONS_DESC_1 + TRUE_ANSWER_DESC, expectedMessage);
        // importance prefix missing
        assertParseFailure(parser, TRUE_ANSWER_DESC + OPTIONS_DESC_1 + QUESTION_DESC_1 + VALID_IMPORTANCE_1,
                expectedMessage);
    }

    @Test
    void parse_fieldsInvalid_failure() {
        // invalid answer
        assertParseFailure(parser, QUESTION_DESC_1 + OPTIONS_DESC_1 + ANSWER_DESC_1 + IMPORTANCE_DESC_1,
                MESSAGE_ANSWER_INVALID);
        // invalid question
        assertParseFailure(parser, INVALID_QUESTION_DESC + TRUE_ANSWER_DESC + IMPORTANCE_DESC_1,
                Name.MESSAGE_CONSTRAINTS);
        // invalid importance
        assertParseFailure(parser,
                QUESTION_DESC_1 + TRUE_ANSWER_DESC + INVALID_IMPORTANCE_DESC,
                Importance.MESSAGE_CONSTRAINTS);
    }

}

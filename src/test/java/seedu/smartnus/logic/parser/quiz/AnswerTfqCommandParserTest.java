package seedu.smartnus.logic.parser.quiz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smartnus.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.smartnus.logic.commands.quiz.AnswerTfqCommand;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.ReadOnlySmartNus;
import seedu.smartnus.model.quiz.QuizManager;
import seedu.smartnus.model.util.SampleDataUtil;


class AnswerTfqCommandParserTest {

    private ReadOnlySmartNus smartNus;
    private QuizManager quizManager;
    private AnswerTfqCommandParser answerTfqCommandParser;

    @BeforeEach
    public void setUp() {
        smartNus = SampleDataUtil.getSampleSmartNus();
        quizManager = new QuizManager(smartNus.getQuestionList());
        answerTfqCommandParser = new AnswerTfqCommandParser();
    }

    @Test
    public void parse_validArgs() throws ParseException {
        assertEquals(answerTfqCommandParser.parse("t", quizManager), new AnswerTfqCommand("t", quizManager));
        assertEquals(answerTfqCommandParser.parse("T", quizManager), new AnswerTfqCommand("T", quizManager));
        assertEquals(answerTfqCommandParser.parse("f", quizManager), new AnswerTfqCommand("f", quizManager));
        assertEquals(answerTfqCommandParser.parse("F", quizManager), new AnswerTfqCommand("F", quizManager));
        assertEquals(answerTfqCommandParser.parse("True", quizManager),
                new AnswerTfqCommand("True", quizManager));
        assertEquals(answerTfqCommandParser.parse("true", quizManager),
                new AnswerTfqCommand("true", quizManager));
        assertEquals(answerTfqCommandParser.parse("False", quizManager),
                new AnswerTfqCommand("False", quizManager));
        assertEquals(answerTfqCommandParser.parse("false", quizManager),
                new AnswerTfqCommand("false", quizManager));
        assertEquals(answerTfqCommandParser.parse("TRUE", quizManager),
                new AnswerTfqCommand("TRUE", quizManager));
        assertEquals(answerTfqCommandParser.parse("FALSE", quizManager),
                new AnswerTfqCommand("FALSE", quizManager));
        assertEquals(answerTfqCommandParser.parse("TrUe", quizManager),
                new AnswerTfqCommand("TrUe", quizManager));
    }

    @Test
    public void parse_invalidArgs() {
        assertThrows(ParseException.class, () -> answerTfqCommandParser.parse("E", quizManager));
        assertThrows(ParseException.class, () -> answerTfqCommandParser.parse("asdf", quizManager));
        assertThrows(ParseException.class, () -> answerTfqCommandParser.parse("tt", quizManager));
        assertThrows(ParseException.class, () -> answerTfqCommandParser.parse("tf", quizManager));
        assertThrows(ParseException.class, () -> answerTfqCommandParser.parse("truet", quizManager));
        assertThrows(ParseException.class, () -> answerTfqCommandParser.parse("truefalse", quizManager));
        assertThrows(ParseException.class, () -> answerTfqCommandParser.parse("truee", quizManager));
    }



}

package seedu.smartnus.logic.parser.quiz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.smartnus.logic.commands.quiz.AnswerMcqCommand;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.ModelManager;
import seedu.smartnus.model.ReadOnlySmartNus;
import seedu.smartnus.model.UserPrefs;
import seedu.smartnus.model.quiz.QuizManager;
import seedu.smartnus.model.util.SampleDataUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smartnus.testutil.Assert.assertThrows;


class AnswerTfCommandParserTest {

    private ReadOnlySmartNus smartNus;
    private QuizManager quizManager;
    private AnswerTfCommandParser answerTfCommandParser;
    private Model model;

    @BeforeEach
    public void setUp() {
        smartNus = SampleDataUtil.getSampleSmartNus();
        quizManager = new QuizManager(smartNus.getQuestionList());
        model = new ModelManager(smartNus, new UserPrefs());
        answerTfCommandParser = new AnswerTfCommandParser();
    }

    @Test
    public void parse_validArgs() throws ParseException {
        assertEquals(answerTfCommandParser.parse("t", quizManager), new AnswerMcqCommand("t", quizManager));
        assertEquals(answerTfCommandParser.parse("T", quizManager), new AnswerMcqCommand("T", quizManager));
        assertEquals(answerTfCommandParser.parse("f", quizManager), new AnswerMcqCommand("f", quizManager));
        assertEquals(answerTfCommandParser.parse("F", quizManager), new AnswerMcqCommand("F", quizManager));
        assertEquals(answerTfCommandParser.parse("True", quizManager),
                new AnswerMcqCommand("True", quizManager));
        assertEquals(answerTfCommandParser.parse("true", quizManager),
                new AnswerMcqCommand("true", quizManager));
        assertEquals(answerTfCommandParser.parse("False", quizManager),
                new AnswerMcqCommand("False", quizManager));
        assertEquals(answerTfCommandParser.parse("false", quizManager),
                new AnswerMcqCommand("false", quizManager));
        assertEquals(answerTfCommandParser.parse("TRUE", quizManager),
                new AnswerMcqCommand("TRUE", quizManager));
        assertEquals(answerTfCommandParser.parse("FALSE", quizManager),
                new AnswerMcqCommand("FALSE", quizManager));
        assertEquals(answerTfCommandParser.parse("TrUe", quizManager),
                new AnswerMcqCommand("TrUe", quizManager));
    }

    @Test
    public void parse_invalidArgs() {
        assertThrows(ParseException.class, () -> answerTfCommandParser.parse("E", quizManager));
        assertThrows(ParseException.class, () -> answerTfCommandParser.parse("asdf", quizManager));
        assertThrows(ParseException.class, () -> answerTfCommandParser.parse("tt", quizManager));
        assertThrows(ParseException.class, () -> answerTfCommandParser.parse("tf", quizManager));
        assertThrows(ParseException.class, () -> answerTfCommandParser.parse("truet", quizManager));
        assertThrows(ParseException.class, () -> answerTfCommandParser.parse("truefalse", quizManager));
        assertThrows(ParseException.class, () -> answerTfCommandParser.parse("truee", quizManager));
    }



}

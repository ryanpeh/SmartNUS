package seedu.smartnus.logic.parser.quiz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smartnus.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.smartnus.logic.commands.quiz.AnswerTfCommand;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.ReadOnlySmartNus;
import seedu.smartnus.model.quiz.QuizManager;
import seedu.smartnus.model.util.SampleDataUtil;


class AnswerTfCommandParserTest {

    private ReadOnlySmartNus smartNus;
    private QuizManager quizManager;
    private AnswerTfCommandParser answerTfCommandParser;

    @BeforeEach
    public void setUp() {
        smartNus = SampleDataUtil.getSampleSmartNus();
        quizManager = new QuizManager(smartNus.getQuestionList());
        answerTfCommandParser = new AnswerTfCommandParser();
    }

    @Test
    public void parse_validArgs() throws ParseException {
        assertEquals(answerTfCommandParser.parse("t", quizManager), new AnswerTfCommand("t", quizManager));
        assertEquals(answerTfCommandParser.parse("T", quizManager), new AnswerTfCommand("T", quizManager));
        assertEquals(answerTfCommandParser.parse("f", quizManager), new AnswerTfCommand("f", quizManager));
        assertEquals(answerTfCommandParser.parse("F", quizManager), new AnswerTfCommand("F", quizManager));
        assertEquals(answerTfCommandParser.parse("True", quizManager),
                new AnswerTfCommand("True", quizManager));
        assertEquals(answerTfCommandParser.parse("true", quizManager),
                new AnswerTfCommand("true", quizManager));
        assertEquals(answerTfCommandParser.parse("False", quizManager),
                new AnswerTfCommand("False", quizManager));
        assertEquals(answerTfCommandParser.parse("false", quizManager),
                new AnswerTfCommand("false", quizManager));
        assertEquals(answerTfCommandParser.parse("TRUE", quizManager),
                new AnswerTfCommand("TRUE", quizManager));
        assertEquals(answerTfCommandParser.parse("FALSE", quizManager),
                new AnswerTfCommand("FALSE", quizManager));
        assertEquals(answerTfCommandParser.parse("TrUe", quizManager),
                new AnswerTfCommand("TrUe", quizManager));
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

package seedu.smartnus.logic.parser.quiz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smartnus.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.smartnus.logic.commands.quiz.AnswerMcqCommand;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.ReadOnlySmartNus;
import seedu.smartnus.model.quiz.QuizManager;
import seedu.smartnus.model.util.SampleDataUtil;


class AnswerMcqCommandParserTest {

    private ReadOnlySmartNus smartNus;
    private QuizManager quizManager;
    private AnswerMcqCommandParser answerMcqCommandParser;

    @BeforeEach
    public void setUp() {
        smartNus = SampleDataUtil.getSampleSmartNus();
        quizManager = new QuizManager(smartNus.getQuestionList());
        answerMcqCommandParser = new AnswerMcqCommandParser();
    }

    @Test
    public void parse_validArgs() throws ParseException {
        assertEquals(answerMcqCommandParser.parse("a", quizManager), new AnswerMcqCommand("a", quizManager));
        assertEquals(answerMcqCommandParser.parse("b", quizManager), new AnswerMcqCommand("b", quizManager));
        assertEquals(answerMcqCommandParser.parse("c", quizManager), new AnswerMcqCommand("c", quizManager));
        assertEquals(answerMcqCommandParser.parse("d", quizManager), new AnswerMcqCommand("d", quizManager));
        assertEquals(answerMcqCommandParser.parse("A", quizManager), new AnswerMcqCommand("A", quizManager));
        assertEquals(answerMcqCommandParser.parse("B", quizManager), new AnswerMcqCommand("B", quizManager));
        assertEquals(answerMcqCommandParser.parse("C", quizManager), new AnswerMcqCommand("C", quizManager));
        assertEquals(answerMcqCommandParser.parse("D", quizManager), new AnswerMcqCommand("D", quizManager));
    }

    @Test
    public void parse_invalidArgs() {
        assertThrows(ParseException.class, () -> answerMcqCommandParser.parse("E", quizManager));
        assertThrows(ParseException.class, () -> answerMcqCommandParser.parse("e", quizManager));
        assertThrows(ParseException.class, () -> answerMcqCommandParser.parse("ab", quizManager));
    }



}

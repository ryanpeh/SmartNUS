package seedu.smartnus.logic.parser.quiz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smartnus.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.smartnus.logic.commands.quiz.AnswerSaqCommand;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.ReadOnlySmartNus;
import seedu.smartnus.model.quiz.QuizManager;
import seedu.smartnus.model.util.SampleDataUtil;


class AnswerSaqCommandParserTest {

    private ReadOnlySmartNus smartNus;
    private QuizManager quizManager;
    private AnswerSaqCommandParser answerSaqCommandParser;

    @BeforeEach
    public void setUp() {
        smartNus = SampleDataUtil.getSampleSmartNus();
        quizManager = new QuizManager(smartNus.getQuestionList());
        answerSaqCommandParser = new AnswerSaqCommandParser();
    }

    @Test
    public void parse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> answerSaqCommandParser.parse(null, quizManager));
    }

    @Test
    public void parse_validArgs() throws ParseException {
        assertEquals(answerSaqCommandParser.parse("ans/abc def ans/abs", quizManager),
                new AnswerSaqCommand("abc def ans/abs", quizManager));
        assertEquals(answerSaqCommandParser.parse("ans/   abc def ans/abs  ", quizManager),
                new AnswerSaqCommand("abc def ans/abs", quizManager));
    }

    @Test
    public void parse_invalidArgs() {
        assertThrows(ParseException.class, () -> answerSaqCommandParser.parse("", quizManager));
        assertThrows(ParseException.class, () -> answerSaqCommandParser.parse("anse/", quizManager));
        assertThrows(ParseException.class, () -> answerSaqCommandParser.parse("abcd/", quizManager));
        assertThrows(ParseException.class, () -> answerSaqCommandParser.parse("answer", quizManager));
    }
}

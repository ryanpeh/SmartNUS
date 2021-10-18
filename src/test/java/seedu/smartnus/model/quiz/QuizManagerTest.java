package seedu.smartnus.model.quiz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.smartnus.model.ReadOnlySmartNus;
import seedu.smartnus.model.quiz.exceptions.QuizOutOfBoundException;
import seedu.smartnus.model.util.SampleDataUtil;


public class QuizManagerTest {

    private ReadOnlySmartNus smartNus;
    private QuizManager quizManager;

    @BeforeEach
    public void setUp() {
        smartNus = SampleDataUtil.getSampleSmartNus();
        quizManager = new QuizManager(smartNus.getQuestionList());
    }

    @Test
    public void getCurrentQuestions_returnsCorrectQuestion() {
        assertEquals(quizManager.currQuestion(), smartNus.getQuestionList().get(0));
    }

    @Test
    public void nextQuestion_success() {
        quizManager.nextQuestion();
        assertEquals(quizManager.getCurrentIndex(), 1);
    }

    @Test
    public void prevQuestion_throwsQuizOutOfBoundException() {
        assertThrows(QuizOutOfBoundException.class, () -> quizManager.prevQuestion());
    }

    @Test
    public void getTotalQuestions_returnsCorrectNumber() {
        assertEquals(quizManager.getTotalQuestions(), smartNus.getQuestionList().size());
    }

    @Test
    public void getCurrentIndex_returnsCorrectIndex() {
        assertEquals(quizManager.getCurrentIndex(), 0);
    }
}

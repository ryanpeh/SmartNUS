package seedu.smartnus.logic.commands.quiz;

import static seedu.smartnus.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.smartnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.smartnus.testutil.TypicalSmartNus.getTypicalSmartNus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.smartnus.commons.core.index.Index;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.ModelManager;
import seedu.smartnus.model.UserPrefs;
import seedu.smartnus.model.question.predicate.ShowAllQuestionsPredicate;
import seedu.smartnus.model.question.predicate.ShowQuestionIndexPredicate;


class QuizCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSmartNus(), new UserPrefs());
        expectedModel = new ModelManager(model.getSmartNus(), new UserPrefs());
    }

    @Test
    void execute_startQuizWithoutArguments_success() {
        assertCommandSuccess(new QuizCommand(new ShowAllQuestionsPredicate()), model,
                QuizCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    void execute_quizNoQuestions() {
        assertCommandFailure(new QuizCommand(new ShowQuestionIndexPredicate(Index.fromOneBased(100))), model,
                QuizCommand.MESSAGE_NO_QUESTIONS);
    }
}

package seedu.smartnus.logic.commands;

import static seedu.smartnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.smartnus.testutil.TypicalQuestions.getTypicalSmartNus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.smartnus.logic.commands.quiz.QuizCommand;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.ModelManager;
import seedu.smartnus.model.UserPrefs;
import seedu.smartnus.model.question.predicate.ShowAllQuestionsPredicate;


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
}

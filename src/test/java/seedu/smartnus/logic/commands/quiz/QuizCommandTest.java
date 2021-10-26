package seedu.smartnus.logic.commands.quiz;

import static seedu.smartnus.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.smartnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.smartnus.testutil.TypicalQuestions.getTypicalSmartNus;

import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.smartnus.commons.core.index.Index;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.ModelManager;
import seedu.smartnus.model.UserPrefs;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.question.predicate.ShowAllQuestionsPredicate;
import seedu.smartnus.model.question.predicate.ShowQuestionIndexPredicate;



class QuizCommandTest {

    private Model model;
    private Model expectedModel;
    private ArrayList<Predicate<Question>> filterPredicates;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSmartNus(), new UserPrefs());
        expectedModel = new ModelManager(model.getSmartNus(), new UserPrefs());
        filterPredicates = new ArrayList<>();
    }

    @Test
    void execute_startQuizWithoutArguments_success() {
        filterPredicates.add(new ShowAllQuestionsPredicate());
        assertCommandSuccess(new QuizCommand(filterPredicates, null), model,
                QuizCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    void execute_quizNoQuestions() {
        filterPredicates.add(new ShowQuestionIndexPredicate(Index.fromOneBased(100)));
        assertCommandFailure(new QuizCommand(filterPredicates, null), model,
                QuizCommand.MESSAGE_NO_QUESTIONS);
    }
}

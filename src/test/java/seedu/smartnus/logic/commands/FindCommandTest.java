package seedu.smartnus.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartnus.commons.core.Messages.MESSAGE_QUESTIONS_LISTED_OVERVIEW;
import static seedu.smartnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.smartnus.logic.commands.CommandUtil.QUESTION_KEYWORD;
import static seedu.smartnus.testutil.TypicalQuestions.FIONA;
import static seedu.smartnus.testutil.TypicalSmartNus.getTypicalSmartNus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.smartnus.model.Model;
import seedu.smartnus.model.ModelManager;
import seedu.smartnus.model.UserPrefs;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.question.predicates.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalSmartNus(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalSmartNus(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        ArrayList<Predicate<Question>> firstPredicateArr = new ArrayList<>();
        firstPredicateArr.add(firstNamePredicate);
        ArrayList<Predicate<Question>> secondPredicateArr = new ArrayList<>();
        secondPredicateArr.add(secondNamePredicate);
        FindCommand findFirstCommand = new FindCommand(firstPredicateArr);
        FindCommand findSecondCommand = new FindCommand(secondPredicateArr);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicateArr);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different question -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noQuestionFound() {
        String expectedMessage = String.format(MESSAGE_QUESTIONS_LISTED_OVERVIEW, 0);
        ArrayList<Predicate<Question>> arr = new ArrayList<>();
        NameContainsKeywordsPredicate predicate = prepareNamePredicate(" ");
        arr.add(predicate);
        FindCommand command = new FindCommand(arr);
        expectedModel.updateFilteredQuestionList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredQuestionList());
        assertEquals(model.getPanel(), QUESTION_KEYWORD);
    }

    @Test
    public void execute_multipleKeywords_oneQuestionFound() {
        String expectedMessage = String.format(MESSAGE_QUESTIONS_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate("Fiona Kunz");
        ArrayList<Predicate<Question>> arr = new ArrayList<>();
        arr.add(predicate);
        FindCommand command = new FindCommand(arr);
        expectedModel.updateFilteredQuestionList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA), model.getFilteredQuestionList());
        assertEquals(model.getPanel(), QUESTION_KEYWORD);
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

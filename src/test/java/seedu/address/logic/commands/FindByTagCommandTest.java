package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_QUESTIONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalQuestions.ALICE;
import static seedu.address.testutil.TypicalQuestions.BENSON;
import static seedu.address.testutil.TypicalQuestions.DANIEL;
import static seedu.address.testutil.TypicalQuestions.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.question.TagsContainKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindByTagCommand}.
 */
public class FindByTagCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        TagsContainKeywordsPredicate firstPredicate =
                new TagsContainKeywordsPredicate(Collections.singletonList("first"));
        TagsContainKeywordsPredicate secondPredicate =
                new TagsContainKeywordsPredicate(Collections.singletonList("second"));

        FindByTagCommand findFirstCommand = new FindByTagCommand(firstPredicate);
        FindByTagCommand findSecondCommand = new FindByTagCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindByTagCommand findFirstCommandCopy = new FindByTagCommand(firstPredicate);
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
        TagsContainKeywordsPredicate predicate = preparePredicate(" ");
        FindByTagCommand command = new FindByTagCommand(predicate);
        expectedModel.updateFilteredQuestionList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredQuestionList());
    }

    @Test
    public void execute_multipleKeywords_multipleQuestionsFound() {
        String expectedMessage = String.format(MESSAGE_QUESTIONS_LISTED_OVERVIEW, 3);
        TagsContainKeywordsPredicate predicate = preparePredicate("owesMoney friends");
        FindByTagCommand command = new FindByTagCommand(predicate);
        expectedModel.updateFilteredQuestionList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredQuestionList());
    }

    /**
     * Parses {@code userInput} into a {@code TagsContainKeywordsPredicate}.
     */
    private TagsContainKeywordsPredicate preparePredicate(String userInput) {
        return new TagsContainKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

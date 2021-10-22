package seedu.smartnus.ui;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.quiz.QuizManager;


/**
 * An UI component that displays information of a {@code Question}.
 */
public class ChoiceGrid extends UiPart<Region> {

    private static final String FXML = "McqChoiceGrid.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    @FXML
    private GridPane mcqGrid;
    @FXML
    private Button optionA;
    @FXML
    private Button optionB;
    @FXML
    private Button optionC;
    @FXML
    private Button optionD;


    /**
     * Creates a {@code QuestionCard} with the given {@code Question} and index to display.
     */
    public ChoiceGrid(QuizManager quizManager) {
        super(FXML);
        Question question = quizManager.currQuestion();
        ArrayList<Choice> choices = question.getOrderedChoices();
        optionA.setText("A. " + choices.get(0).getTitle());
        optionB.setText("B. " + choices.get(1).getTitle());
        optionC.setText("C. " + choices.get(2).getTitle());
        optionD.setText("D. " + choices.get(3).getTitle());
        // TODO: Remove function below if we're enabling click to select choice.
        disableButtons();
    }

    private void disableButtons() {
        optionA.setDisable(true);
        optionB.setDisable(true);
        optionC.setDisable(true);
        optionD.setDisable(true);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof McqChoiceGrid)) {
            return false;
        }

        // state check
        // McqChoiceGrid card = (McqChoiceGrid) other;
        return false;
    }
}

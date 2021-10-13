package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.choice.Choice;
import seedu.address.model.question.Question;

/**
 * An UI component that displays information of a {@code Question}.
 */
public class QuestionCard extends UiPart<Region> {

    private static final String FXML = "QuestionListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Question question;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label importance;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane choices;

    /**
     * Creates a {@code QuestionCard} with the given {@code Question} and index to display.
     */
    public QuestionCard(Question question, int displayedIndex) {
        super(FXML);
        this.question = question;
        id.setText(displayedIndex + ". ");
        name.setText(question.getName().fullName);
        importance.setText("importance: " + question.getImportance().value);
        question.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        question.getChoices().stream()
                .forEach(choice -> getChoiceLabel(choice));
    }
    
    private void getChoiceLabel(Choice choice) {
        Label choiceLabel = new Label(choice.getTitle());
        if (choice.getIsCorrect()) {
            choiceLabel.getStyleClass().add("correct-choice-bg");
        } else {
            choiceLabel.getStyleClass().add("wrong-choice-bg");
        }
        choices.getChildren().add(choiceLabel);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof QuestionCard)) {
            return false;
        }

        // state check
        QuestionCard card = (QuestionCard) other;
        return id.getText().equals(card.id.getText())
                && question.equals(card.question);
    }
}

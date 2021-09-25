package seedu.address.model.questions;

import seedu.address.model.choice.Choice;
import seedu.address.model.tag.Tag;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import static java.util.Objects.requireNonNull;


public class MultipleChoiceQuestion extends Question {
    private final Set<Choice> choices = new HashSet<>();
    
    public MultipleChoiceQuestion(int id, String title, Set<Tag> tags, Set<Choice> choices) {
        super(id, title, tags);
        requireNonNull(choices);
        this.choices.addAll(choices);
    }

    /**
     * Returns an immutable choice set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Choice> getChoices() {
        return Collections.unmodifiableSet(choices);
    }

    @Override
    public boolean checkAnswer(Choice choice) {
        return choice.getIsCorrect();
    }

    @Override
    public boolean isValidQuestion() {
        return hasOneCorrectAnswer();
    }
    
    private boolean hasOneCorrectAnswer() {
        int correctAnswers = 0;
        for (Choice choice : choices) {
            if (choice.getIsCorrect()) {
                correctAnswers += 1;
            }
        }
        return correctAnswers == 1;
    }
}

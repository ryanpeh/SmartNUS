package seedu.address.model.question;

import java.util.Set;

import seedu.address.model.choice.Choice;
import seedu.address.model.tag.Tag;

public class MultipleChoiceQuestion extends Question {
    public static final int NUMBER_OF_INCORRECT_CHOICES = 3;
    public static final String MESSAGE_INCORRECT_NUMBER_OF_CHOICES = "Must have only 3 incorrect options!";
    public static final String MESSAGE_DUPLICATE_CHOICES = "Options should not be duplicates!";

    public MultipleChoiceQuestion(Name name, Importance importance, Set<Tag> tags,
                                  Set<Choice> choices) {
        super(name, importance, tags, choices);
    }

    /**
     * Returns True if {@code MultipleChoiceQuestion} is valid, false otherwise.A {@code MultipleChoiceQuestion} is
     * valid if it has four choices and exactly one of the choices is correct.
     *
     * @return True if MultipleChoiceQuestion is valid, false otherwise.
     */
    @Override
    public boolean isValidQuestion() {
        int choiceCount = getChoices().size();
        int correctChoiceCount = 0;
        for (Choice choice : getChoices()) {
            if (choice.getIsCorrect()) {
                correctChoiceCount += 1;
            }
        }
        return choiceCount == (NUMBER_OF_INCORRECT_CHOICES + 1) && correctChoiceCount == 1;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof MultipleChoiceQuestion)) {
            return false;
        }

        return super.equals(other);
    }
}

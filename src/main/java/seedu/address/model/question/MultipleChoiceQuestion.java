package seedu.address.model.question;

import java.util.Set;

import seedu.address.model.choice.Choice;
import seedu.address.model.tag.Tag;

public class MultipleChoiceQuestion extends Question {
    public static final int NUMBER_OF_INCORRECT_CHOICES = 3;
    public static final String MESSAGE_INSUFFICIENT_CHOICES = "Must have 3 options!";

    public MultipleChoiceQuestion(Name name, Importance importance, Set<Tag> tags,
                                  Set<Choice> choices) {
        super(name, importance, tags, choices);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof MultipleChoiceQuestion)) {
            return false;
        }

        return super.equals(other);
    }
}

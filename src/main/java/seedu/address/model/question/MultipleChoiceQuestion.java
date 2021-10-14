package seedu.address.model.question;

import java.util.ArrayList;
import java.util.Set;

import seedu.address.model.choice.Choice;
import seedu.address.model.tag.Tag;

public class MultipleChoiceQuestion extends Question {
    public static final int NUMBER_OF_INCORRECT_CHOICES = 3;
    public static final String MESSAGE_INCORRECT_NUMBER_OF_CHOICES = "Must have only 3 options!";
    public static final String MESSAGE_DUPLICATE_CHOICES = "Options should not be duplicates!";

    public MultipleChoiceQuestion(Name name, Importance importance, Set<Tag> tags,
                                  Set<Choice> choices) {
        super(name, importance, tags, choices);
    }

    @Override
    public String getQuestionAndOptions() {
        String title = this.getName().toString();
        ArrayList<Choice> choices = this.getOrderedChoices();
        String options = "\na. " + choices.get(0).getTitle()
                + "  b. " + choices.get(1).getTitle()
                + "  c. " + choices.get(2).getTitle()
                + "  d. " + choices.get(3).getTitle();
        return title + options;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof MultipleChoiceQuestion)) {
            return false;
        }

        return super.equals(other);
    }
}

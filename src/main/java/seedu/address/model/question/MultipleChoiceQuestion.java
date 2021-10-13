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
        ArrayList<Choice> randomisedChoices = this.getRandomisedChoices();
        String options = "\na. " + randomisedChoices.get(0).getTitle() +
                "  b. " + randomisedChoices.get(1).getTitle() +
                "  c. " + randomisedChoices.get(2).getTitle() +
                "  d. " + randomisedChoices.get(3).getTitle();
        return title + options;
    }

    /**
     * Gets the question details and choices. Note: To change once UI is made
     */
    private String getQuestionDetails(Question question) {
        String details = question.getName().toString() + "\n";
        ArrayList<Choice> choices = question.getRandomisedChoices();
        if (question instanceof MultipleChoiceQuestion) {
            details += "a. " + choices.get(0).getTitle() + "\n" +
                    "b. " + choices.get(1).getTitle() + "\n" +
                    "c. " + choices.get(2).getTitle() + "\n" +
                    "d. " + choices.get(3).getTitle() + "\n";
        }
        return details;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof MultipleChoiceQuestion)) {
            return false;
        }

        return super.equals(other);
    }
}

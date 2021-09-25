package seedu.address.model.question;

import seedu.address.model.choice.Choice;
import seedu.address.model.tag.Tag;

import java.util.Set;

public class MultipleChoiceQuestion extends Question {
    public MultipleChoiceQuestion(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Set<Choice> choices) {
        super(name, phone, email, address, tags, choices);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof MultipleChoiceQuestion)) {
            return false;
        }

        Question otherQuestion = (Question) other;
        return otherQuestion.getName().equals(getName())
                && otherQuestion.getPhone().equals(getPhone())
                && otherQuestion.getEmail().equals(getEmail())
                && otherQuestion.getAddress().equals(getAddress())
                && otherQuestion.getTags().equals(getTags());
    }
}

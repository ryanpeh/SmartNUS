package seedu.address.model.question;

import java.util.Set;

import seedu.address.model.choice.Choice;
import seedu.address.model.tag.Tag;

public class MultipleChoiceQuestion extends Question {
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

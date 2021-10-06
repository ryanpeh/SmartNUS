package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.choice.Choice;
import seedu.address.model.question.MultipleChoiceQuestion;
import seedu.address.model.question.Name;
import seedu.address.model.question.Phone;
import seedu.address.model.question.Question;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Question objects.
 */
public class QuestionBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final Choice DEFAULT_CHOICE = new Choice("option 1", true);

    private Name name;
    private Phone phone;
    private Set<Tag> tags;
    private Set<Choice> choices;

    /**
     * Creates a {@code QuestionBuilder} with the default details.
     */
    public QuestionBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        tags = new HashSet<>();
        choices = new HashSet<>();
        choices.add(DEFAULT_CHOICE);
    }

    /**
     * Initializes the QuestionBuilder with the data of {@code questionToCopy}.
     */
    public QuestionBuilder(Question questionToCopy) {
        name = questionToCopy.getName();
        phone = questionToCopy.getPhone();
        tags = new HashSet<>(questionToCopy.getTags());
        choices = new HashSet<>(questionToCopy.getChoices());
    }

    /**
     * Sets the {@code Name} of the {@code Question} that we are building.
     */
    public QuestionBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Question} that we are building.
     */
    public QuestionBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Question} that we are building.
     */
    public QuestionBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Choices} of the {@code Question} that we are building to
     * a set containing choices.
     */
    public QuestionBuilder withChoices(Choice ... choices) {
        this.choices = new HashSet<>();
        for (Choice choice : choices) {
            this.choices.add(choice);
        }
        return this;
    }

    /**
     * Builds the the {@code Question} that we are building.
     */
    public Question build() {
        // TODO: edit when more Question types are supported
        return new MultipleChoiceQuestion(name, phone, tags, choices);
    }

}

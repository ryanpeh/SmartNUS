package seedu.smartnus.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Importance;
import seedu.smartnus.model.question.MultipleChoiceQuestion;
import seedu.smartnus.model.question.Name;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.tag.Tag;
import seedu.smartnus.model.util.SampleDataUtil;

/**
 * A utility class to help with building Question objects.
 */
public class QuestionBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_IMPORTANCE = "1";
    public static final Choice DEFAULT_CHOICE = new Choice("option 1", true);

    private Name name;
    private Importance importance;
    private Set<Tag> tags;
    private Set<Choice> choices;

    /**
     * Creates a {@code QuestionBuilder} with the default details.
     */
    public QuestionBuilder() {
        name = new Name(DEFAULT_NAME);
        importance = new Importance(DEFAULT_IMPORTANCE);
        tags = new HashSet<>();
        choices = new HashSet<>();
        choices.add(DEFAULT_CHOICE);
    }

    /**
     * Initializes the QuestionBuilder with the data of {@code questionToCopy}.
     */
    public QuestionBuilder(Question questionToCopy) {
        name = questionToCopy.getName();
        importance = questionToCopy.getImportance();
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
     * Sets the {@code Importance} of the {@code Question} that we are building.
     */
    public QuestionBuilder withImportance(String importance) {
        this.importance = new Importance(importance);
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
        return new MultipleChoiceQuestion(name, importance, tags, choices);
    }

}

package seedu.smartnus.model.question;

import static seedu.smartnus.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.tag.Tag;

/**
 * Represents a Question in SmartNus.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Question {

    // Identity fields
    private final Name name;
    private final Importance importance;

    private final Set<Tag> tags = new HashSet<>();
    private final Set<Choice> choices = new HashSet<>();
    private final ArrayList<Choice> orderedChoices = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Question(Name name, Importance importance, Set<Tag> tags, Set<Choice> choices) {
        requireAllNonNull(name, importance, tags);
        this.name = name;
        this.importance = importance;
        this.tags.addAll(tags);
        this.choices.addAll(choices);
        this.orderedChoices.addAll(choices);
        Collections.shuffle(orderedChoices);
    }

    /**
     * Checks if a {@code Question} is valid. Conditions for validity depend on the type of {@code Question}.
     * 
     * @return True if Question is valid, else false.
     */
    public abstract boolean isValidQuestion();

    public Name getName() {
        return name;
    }

    public Importance getImportance() {
        return importance;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable choice set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Choice> getChoices() {
        return Collections.unmodifiableSet(choices);
    }

    /**
     * Returns a randomised ArrayList of choices.
     */
    public ArrayList<Choice> getOrderedChoices() {
        return orderedChoices;
    }

    /**
     * Shuffles the order of the choices for the question.
     */
    public void shuffleChoices() {
        Collections.shuffle(orderedChoices);
    }

    public Choice getCorrectChoice() {
        for (Choice choice : choices) {
            if (choice.getIsCorrect()) {
                return choice;
            }
        }
        return null;
    }

    /**
     * Returns true if both questions have the same name.
     * This defines a weaker notion of equality between two questions.
     */
    public boolean isSameQuestion(Question otherQuestion) {
        if (otherQuestion == this) {
            return true;
        }

        return otherQuestion != null
                && otherQuestion.getName().equals(getName());
    }

    /**
     * Returns true if both questions have the same identity and data fields.
     * This defines a stronger notion of equality between two questions.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Question)) {
            return false;
        }

        Question otherQuestion = (Question) other;
        // TODO: Add Choice comparison after Choices can be saved and loaded from storage
        return otherQuestion.getName().equals(getName())
                && otherQuestion.getImportance().equals(getImportance())
                && otherQuestion.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, importance, tags);
    }

    // TODO: Remove maybe when UI is implemented
    public abstract String getQuestionAndOptions();

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        appendImportance(builder);
        appendTags(builder);
        appendChoices(builder);

        return builder.toString();
    }

    private StringBuilder appendImportance(StringBuilder builder) {
        builder.append(getName())
                .append("; Importance: ")
                .append(getImportance());
        return builder;
    }

    private StringBuilder appendTags(StringBuilder builder) {
        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder;
    }

    private StringBuilder appendChoices(StringBuilder builder) {
        Set<Choice> choices = getChoices();
        if (!choices.isEmpty()) { // may be empty for open-ended questions
            builder.append("; Choices: ");
            for (Choice choice: choices) {
                builder.append(choice).append(", ");
            }
            builder.setLength(builder.length() - 2); //remove ", " after last choice
        }
        return builder;
    }

}

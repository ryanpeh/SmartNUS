package seedu.smartnus.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.smartnus.commons.exceptions.IllegalValueException;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Question;

/**
 * Jackson-friendly version of {@link Question}.
 */
class JsonAdaptedChoice {

    private final String title;
    private final boolean isCorrect;

    /**
     * Constructs a {@code JsonAdaptedChoice} with the given choice details.
     */
    @JsonCreator
    public JsonAdaptedChoice(@JsonProperty("title") String title, @JsonProperty("isCorrect") boolean isCorrect) {
        this.title = title;
        this.isCorrect = isCorrect;
    }

    /**
     * Converts a given {@code Choice} into this class for Jackson use.
     */
    public JsonAdaptedChoice(Choice source) {
        title = source.getTitle();
        isCorrect = source.getIsCorrect();
    }

    /**
     * Converts this Jackson-friendly adapted question object into the model's {@code Question} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted question.
     */
    public Choice toModelType() throws IllegalValueException {
        if (!Choice.isValidChoiceTitle(title)) {
            throw new IllegalValueException(Choice.MESSAGE_CONSTRAINTS);
        }
        return new Choice(title, isCorrect);
    }

}

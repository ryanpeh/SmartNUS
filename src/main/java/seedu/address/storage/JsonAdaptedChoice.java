package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.choice.Choice;

public class JsonAdaptedChoice {
    private final Choice choice;

    /**
     * Converts a given {@code Choice} into this class for Jackson use.
     */
    @JsonCreator
    public JsonAdaptedChoice(Choice choice) {
        this.choice = new Choice(choice.getTitle(), choice.getIsCorrect());
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Choice} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted choice.
     */
    public Choice toModelType() throws IllegalValueException {
        if (!Choice.isValidChoiceTitle(choice.getTitle())) {
            throw new IllegalValueException(Choice.MESSAGE_CONSTRAINTS);
        }
        return choice;
    }
}

package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.choice.Choice;
import seedu.address.model.question.MultipleChoiceQuestion;
import seedu.address.model.question.Name;
import seedu.address.model.question.Phone;
import seedu.address.model.question.Question;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Question}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Question's %s field is missing!";

    private final String name;
    private final String phone;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given question details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Question} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Question source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted question object into the model's {@code Question} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted question.
     */
    public Question toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        // TODO: Implement loading functionality for choices
        final List<Choice> questionChoices = new ArrayList<>();

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        final Set<Choice> modelChoices = new HashSet<>(questionChoices);

        // TODO: save question type in json and instantiate correct Question type when more types are supported
        return new MultipleChoiceQuestion(modelName, modelPhone, modelTags, modelChoices);
    }

}

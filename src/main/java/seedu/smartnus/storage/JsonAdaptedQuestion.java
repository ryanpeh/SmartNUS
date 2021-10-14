package seedu.smartnus.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.smartnus.commons.exceptions.IllegalValueException;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Importance;
import seedu.smartnus.model.question.MultipleChoiceQuestion;
import seedu.smartnus.model.question.Name;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Question}.
 */
class JsonAdaptedQuestion {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Question's %s field is missing!";

    private final String name;
    private final String importance;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedChoice> choices = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedQuestion} with the given question details.
     */
    @JsonCreator
    public JsonAdaptedQuestion(@JsonProperty("name") String name, @JsonProperty("importance") String importance,
                               @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                               @JsonProperty("choices") List<JsonAdaptedChoice> choices) {
        this.name = name;
        this.importance = importance;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (choices != null) {
            this.choices.addAll(choices);
        }
    }

    /**
     * Converts a given {@code Question} into this class for Jackson use.
     */
    public JsonAdaptedQuestion(Question source) {
        name = source.getName().fullName;
        importance = source.getImportance().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        choices.addAll(source.getChoices().stream()
                .map(JsonAdaptedChoice::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted question object into the model's {@code Question} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted question.
     */
    public Question toModelType() throws IllegalValueException {
        final List<Tag> questionTags = new ArrayList<>();
        final List<Choice> questionChoices = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            questionTags.add(tag.toModelType());
        }

        for (JsonAdaptedChoice choice : choices) {
            questionChoices.add(choice.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (importance == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Importance.class.getSimpleName()));
        }
        if (!Importance.isValidImportance(importance)) {
            throw new IllegalValueException(Importance.MESSAGE_CONSTRAINTS);
        }

        final Importance modelImportance = new Importance(importance);

        final Set<Tag> modelTags = new HashSet<>(questionTags);

        final Set<Choice> modelChoices = new HashSet<>(questionChoices);

        // TODO: save question type in json and instantiate correct Question type when more types are supported
        return new MultipleChoiceQuestion(modelName, modelImportance, modelTags, modelChoices);
    }

}

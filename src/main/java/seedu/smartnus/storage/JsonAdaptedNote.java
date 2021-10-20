package seedu.smartnus.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.smartnus.commons.exceptions.IllegalValueException;
import seedu.smartnus.model.note.Note;
import seedu.smartnus.model.note.NoteName;
import seedu.smartnus.model.tag.Tag;

public class JsonAdaptedNote {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Note's %s field is missing!";

    private final String title;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedQuestion} with the given question details.
     */
    @JsonCreator
    public JsonAdaptedNote(@JsonProperty("title") String title,
                               @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.title = title;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Question} into this class for Jackson use.
     */
    public JsonAdaptedNote(Note source) {
        title = source.getTitle();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted question object into the model's {@code Question} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted question.
     */
    public Note toModelType() throws IllegalValueException {
        final List<Tag> questionTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            questionTags.add(tag.toModelType());
        }

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    NoteName.class.getSimpleName()));
        }
        if (!NoteName.isValidName(title)) {
            throw new IllegalValueException(NoteName.MESSAGE_CONSTRAINTS);
        }
        final NoteName modelName = new NoteName(title);

        final Set<Tag> modelTags = new HashSet<>(questionTags);

        // TODO: save question type in json and instantiate correct Question type when more types are supported
        return new Note(modelName.fullName, modelTags);
    }

}

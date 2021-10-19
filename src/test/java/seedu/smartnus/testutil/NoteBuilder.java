package seedu.smartnus.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.smartnus.model.note.Note;
import seedu.smartnus.model.note.NoteName;
import seedu.smartnus.model.tag.Tag;
import seedu.smartnus.model.util.SampleDataUtil;

public class NoteBuilder {
    public static final String DEFAULT_NOTE = "CS2103T is super fun";

    private String noteName;
    private Set<Tag> tags;

    /**
     * Creates a {@code QuestionBuilder} with the default details.
     */
    public NoteBuilder() {
        noteName = DEFAULT_NOTE;
        tags = new HashSet<>();
    }

    /**
     * Initializes the QuestionBuilder with the data of {@code questionToCopy}.
     */
    public NoteBuilder(Note noteToCopy) {
        noteName = noteToCopy.getTitle();
        tags = new HashSet<>(noteToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Question} that we are building.
     */
    public NoteBuilder withName(String noteName) {
        this.noteName = noteName;
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Question} that we are building.
     */
    public NoteBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Builds the the {@code Question} that we are building.
     */
    public Note build() {
        // TODO: edit when more Question types are supported
        return new Note(noteName, tags);
    }
}

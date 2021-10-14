package seedu.smartnus.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.smartnus.logic.commands.EditCommand;
import seedu.smartnus.logic.commands.EditCommand.EditQuestionDescriptor;
import seedu.smartnus.model.question.Importance;
import seedu.smartnus.model.question.Name;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.tag.Tag;

/**
 * A utility class to help with building EditQuestionDescriptor objects.
 */
public class EditQuestionDescriptorBuilder {

    private EditQuestionDescriptor descriptor;

    public EditQuestionDescriptorBuilder() {
        descriptor = new EditQuestionDescriptor();
    }

    public EditQuestionDescriptorBuilder(EditCommand.EditQuestionDescriptor descriptor) {
        this.descriptor = new EditQuestionDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditQuestionDescriptor} with fields containing {@code question}'s details
     */
    public EditQuestionDescriptorBuilder(Question question) {
        descriptor = new EditCommand.EditQuestionDescriptor();
        descriptor.setName(question.getName());
        descriptor.setImportance(question.getImportance());
        descriptor.setTags(question.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditQuestionDescriptor} that we are building.
     */
    public EditQuestionDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Importance} of the {@code EditQuestionDescriptor} that we are building.
     */
    public EditQuestionDescriptorBuilder withImportance(String importance) {
        descriptor.setImportance(new Importance(importance));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditQuestionDescriptor}
     * that we are building.
     */
    public EditQuestionDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditCommand.EditQuestionDescriptor build() {
        return descriptor;
    }
}

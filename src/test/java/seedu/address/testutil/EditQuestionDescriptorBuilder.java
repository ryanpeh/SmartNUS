package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.question.Importance;
import seedu.address.model.question.Name;
import seedu.address.model.question.Question;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditQuestionDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditQuestionDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditQuestionDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code question}'s details
     */
    public EditQuestionDescriptorBuilder(Question question) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(question.getName());
        descriptor.setImportance(question.getImportance());
        descriptor.setTags(question.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditQuestionDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Importance} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditQuestionDescriptorBuilder withImportance(String importance) {
        descriptor.setImportance(new Importance(importance));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditQuestionDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}

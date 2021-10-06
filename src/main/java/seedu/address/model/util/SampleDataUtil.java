package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.choice.Choice;
import seedu.address.model.question.Email;
import seedu.address.model.question.MultipleChoiceQuestion;
import seedu.address.model.question.Name;
import seedu.address.model.question.Phone;
import seedu.address.model.question.Question;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Question[] getSamplePersons() {
        return new Question[] {
            new MultipleChoiceQuestion(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                getTagSet("friends"), getChoiceSet(new Choice("first choice", true),
                    new Choice("second choice", false))),
            new MultipleChoiceQuestion(new Name("Bernice Yu"), new Phone("99272758"),
                new Email("berniceyu@example.com"),
                getTagSet("colleagues", "friends"), getChoiceSet(new Choice("first choice", true),
                    new Choice("second choice", false))),
            new MultipleChoiceQuestion(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                new Email("charlotte@example.com"),
                getTagSet("neighbours"), getChoiceSet(new Choice("first choice", true),
                    new Choice("second choice", false))),
            new MultipleChoiceQuestion(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                getTagSet("family"), getChoiceSet(new Choice("first choice", true),
                    new Choice("second choice", false))),
            new MultipleChoiceQuestion(new Name("Irfan Ibrahim"), new Phone("92492021"),
                    new Email("irfan@example.com"),
                getTagSet("classmates"), getChoiceSet(new Choice("first choice", true),
                    new Choice("second choice", false))),
            new MultipleChoiceQuestion(new Name("Roy Balakrishnan"), new Phone("92624417"),
                    new Email("royb@example.com"),
                getTagSet("colleagues"), getChoiceSet(new Choice("first choice", true),
                    new Choice("second choice", false)))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Question sampleQuestion : getSamplePersons()) {
            sampleAb.addQuestion(sampleQuestion);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a choice set containing the list of strings given.
     */
    public static Set<Choice> getChoiceSet(Choice... choices) {
        return Arrays.stream(choices)
                .collect(Collectors.toSet());
    }

}

package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.choice.Choice;
import seedu.address.model.question.Importance;
import seedu.address.model.question.MultipleChoiceQuestion;
import seedu.address.model.question.Name;
import seedu.address.model.question.Question;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Question[] getSamplePersons() {
        return new Question[] {
            new MultipleChoiceQuestion(new Name("Alex Yeoh"), new Importance("87438807"),
                getTagSet("friends"), getChoiceSet(new Choice("first choice", true),
                    new Choice("second choice", false))),
            new MultipleChoiceQuestion(new Name("Bernice Yu"), new Importance("99272758"),
                getTagSet("colleagues", "friends"), getChoiceSet(new Choice("first choice", true),
                    new Choice("second choice", false))),
            new MultipleChoiceQuestion(new Name("Charlotte Oliveiro"), new Importance("93210283"),
                getTagSet("neighbours"), getChoiceSet(new Choice("first choice", true),
                    new Choice("second choice", false))),
            new MultipleChoiceQuestion(new Name("David Li"), new Importance("91031282"),
                getTagSet("family"), getChoiceSet(new Choice("first choice", true),
                    new Choice("second choice", false))),
            new MultipleChoiceQuestion(new Name("Irfan Ibrahim"), new Importance("92492021"),
                getTagSet("classmates"), getChoiceSet(new Choice("first choice", true),
                    new Choice("second choice", false))),
            new MultipleChoiceQuestion(new Name("Roy Balakrishnan"), new Importance("92624417"),
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

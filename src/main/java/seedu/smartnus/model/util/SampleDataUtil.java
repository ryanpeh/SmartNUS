package seedu.smartnus.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.smartnus.model.ReadOnlySmartNus;
import seedu.smartnus.model.SmartNus;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.note.Note;
import seedu.smartnus.model.question.Importance;
import seedu.smartnus.model.question.MultipleChoiceQuestion;
import seedu.smartnus.model.question.Name;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.question.TrueFalseQuestion;
import seedu.smartnus.model.tag.Tag;

/**
 * Contains utility methods for populating {@code SmartNus} with sample data.
 */
public class SampleDataUtil {
    public static Question[] getSampleQuestions() {
        return new Question[] {
            new MultipleChoiceQuestion(
                    new Name("The mean and variance of a random variable X are 5 and 8. What is E(X)?"),
                    new Importance("2"), getTagSet("ST2334"),
                    getChoiceSet(new Choice("5", true), new Choice("4", false),
                            new Choice("7", false), new Choice("8", false))
            ),
            new MultipleChoiceQuestion(
                    new Name("What is the maximum line length according to CS2103T's coding standard?"),
                    new Importance("2"), getTagSet("CS2103T", "Java", "style"),
                    getChoiceSet(new Choice("120", true), new Choice("110", false),
                            new Choice("100", false), new Choice("130", false))
            ),
            new TrueFalseQuestion(
                    new Name("Grey-box test case design is a mixture of specification-based"
                            + " and implementation-based approaches.?"),
                    new Importance("2"), getTagSet("CS2103T", "Java", "Testing"),
                    getChoiceSet(new Choice("True", true), new Choice("False", false))
            ),
            new MultipleChoiceQuestion(
                    new Name("The mean of a random variable X is 10 and E(X^2) = 20. "
                            + "What is the standard deviation of X?"),
                    new Importance("2"), getTagSet("ST2334"),
                    getChoiceSet(new Choice("8.94", true), new Choice("80.0", false),
                            new Choice("60.0", false), new Choice("7.78", false))
            ),
            new MultipleChoiceQuestion(new Name("Which of the following logic gates is a universal gate?"),
                    new Importance("1"), getTagSet("CS2100"),
                    getChoiceSet(new Choice("NAND", true), new Choice("OR", false),
                            new Choice("XOR", false), new Choice("AND", false))
            ),
            new TrueFalseQuestion(
                    new Name("Data abstraction is removing all data-related information"
                            + "from the view to create a higher level abstraction."),
                    new Importance("2"), getTagSet("CS2103T", "Java", "Design"),
                    getChoiceSet(new Choice("True", false), new Choice("False", true))
            ),
            new MultipleChoiceQuestion(new Name("Convert the following MIPS instruction into hexadecimal: "
                    + "lw $t9, 0($t7)"),
                    new Importance("3"), getTagSet("CS2100", "MIPS"),
                    getChoiceSet(new Choice("0x8df90000", true), new Choice("0x8ed90000", false),
                            new Choice("0x8df80000", false), new Choice("0x8de90000", false))
            ),
            new MultipleChoiceQuestion(
                    new Name("What are class diagrams that are used to model the problem domain called?"),
                    new Importance("3"), getTagSet("CS2103T"),
                    getChoiceSet(new Choice("Object oriented domain models", true),
                            new Choice("Object diagrams", false), new Choice("Sequence diagrams", false),
                            new Choice("Problem domain diagrams", false))
            )
        };
    }

    public static Note[] getSampleNotes() {
        return new Note[] {
            new Note("CS2103T has PE dry run in Week 11"),
            new Note("CS2100 has assignment 3 coming up"),
            new Note("Remember to not fall behind in CS mods!"),
            new Note("Remember to do the CS2103T quiz over the weekends."),
            new Note("Complete the TP as soon as possible, it can get intense.")
        };
    }

    public static ReadOnlySmartNus getSampleSmartNus() {
        SmartNus sampleSmartNus = new SmartNus();
        for (Question sampleQuestion : getSampleQuestions()) {
            sampleSmartNus.addQuestion(sampleQuestion);
        }
        for (Note sampleNote : getSampleNotes()) {
            sampleSmartNus.addNote(sampleNote);
        }
        return sampleSmartNus;
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

package seedu.smartnus.testutil;

import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_IMPORTANCE_AMY;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_IMPORTANCE_BOB;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import seedu.smartnus.model.SmartNus;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Question;

/**
 * A utility class containing a list of {@code Question} objects to be used in tests.
 */
public class TypicalQuestions {
    public static final int MCQ_ONE_BASED_INDEX = 8;
    public static final int TF_ONE_BASED_INDEX = 9;
    public static final int SAQ_ONE_BASED_INDEX = 10;

    public static final Question ALICE = new QuestionBuilder().withName("Alice Pauline")
            .withImportance("1")
            .withTags("friends").build();
    public static final Question BENSON = new QuestionBuilder().withName("Benson Meier")
            .withImportance("2")
            .withTags("owesMoney", "friends")
            .withChoices(new Choice("good guy", true),
                    new Choice("wrong good guy", false),
                    new Choice("wrong 2", false),
                    new Choice("wrong 3", false)).build();
    public static final Question MCQ_QUESTION = new QuestionBuilder().withName("What is 1+1?")
            .withImportance("1")
            .withChoices(new Choice("2", true),
                    new Choice("1", false), new Choice("4", false),
                    new Choice("3", false)).build();
    public static final Question TF_QUESTION = new QuestionBuilder().withName("Is 1+1 = 2?")
            .withImportance("2")
            .withChoices(new Choice(Choice.TRUE_CHOICE_TITLE, true),
                    new Choice(Choice.FALSE_CHOICE_TITLE, false)).buildTrueFalse();
    public static final Question SAQ_QUESTION = new QuestionBuilder()
            .withName("Harry, you're a wizard. Where is this quote from?")
            .withImportance("2")
            .withChoices(new Choice("Harry Potter and the Philosopher's Stone",
                    true, new HashSet<>(List.of("harry", "potter")))).buildSaq();
    public static final Question CARL = new QuestionBuilder().withName("Carl Kurz").withImportance("3")
            .build();
    public static final Question DANIEL = new QuestionBuilder().withName("Daniel Meier").withImportance("1")
            .withTags("friends").build();
    public static final Question ELLE = new QuestionBuilder().withName("Elle Meyer").withImportance("2")
            .build();
    public static final Question FIONA = new QuestionBuilder().withName("Fiona Kunz").withImportance("3")
            .build();
    public static final Question GEORGE = new QuestionBuilder().withName("George Best").withImportance("1")
            .build();

    // Manually added
    public static final Question HOON = new QuestionBuilder().withName("Hoon Meier").withImportance("2")
            .build();
    public static final Question IDA = new QuestionBuilder().withName("Ida Mueller").withImportance("3")
            .build();

    // Manually added - Question's details found in {@code CommandTestUtil}
    public static final Question AMY = new QuestionBuilder().withName(VALID_NAME_AMY)
            .withImportance(VALID_IMPORTANCE_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Question BOB = new QuestionBuilder().withName(VALID_NAME_BOB)
            .withImportance(VALID_IMPORTANCE_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalQuestions() {} // prevents instantiation

    /**
     * Returns an {@code SmartNus} with all the typical questions.
     */
    public static SmartNus getTypicalSmartNusQuestions() {
        SmartNus ab = new SmartNus();
        for (Question question : getTypicalQuestions()) {
            ab.addQuestion(question);
        }
        return ab;
    }

    public static List<Question> getTypicalQuestions() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE, MCQ_QUESTION,
                TF_QUESTION, SAQ_QUESTION));
    }
}

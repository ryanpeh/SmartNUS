package seedu.smartnus.model.question;

import java.util.Set;

import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.tag.Tag;

public class ShortAnswerQuestion extends Question {
    public static final String MESSAGE_VALID_SAQ = "Short Answer Questions must have exactly one choice"
            + " which is the correct answer.";
    public static final String MESSAGE_OPTIONS_INVALID = "Short Answer Questions should not have"
        + "wrong options specified";

    public ShortAnswerQuestion(Name name, Importance importance, Set<Tag> tags,
                                  Set<Choice> choices) {
        super(name, importance, tags, choices);
    }

    public ShortAnswerQuestion(Name name, Importance importance, Set<Tag> tags,
                                  Set<Choice> choices, Statistic statistic) {
        super(name, importance, tags, choices, statistic);
    }

    /**
     * Returns True if {@code ShortAnswerQuestion} is valid, false otherwise.A {@code ShortAnswerQuestion} is
     * valid if it has exactly one choice which is correct.
     *
     * @return True if this ShortAnswerQuestion is valid, false otherwise.
     */
    @Override
    public boolean isValidQuestion() {
        Set<Choice> choices = getChoices();
        int correctChoiceCount = 0;
        for (Choice choice : choices) {
            if (choice.getIsCorrect()) {
                correctChoiceCount += 1;
            }
        }
        return correctChoiceCount == 1 && choices.size() == 1;
    }

    @Override
    public String getQuestionAndOptions() {
        return this.getName().toString();
    }

    @Override
    public boolean attemptAndCheckAnswer(Choice choiceToCheck) {
        getStatistic().addAttempt();
        if (isCorrectAnswer(choiceToCheck)) {
            getStatistic().addCorrect();
            return true;
        }
        return false;
    }

    @Override
    public boolean isCorrectAnswer(Choice choiceToCheck) {
        Set<Choice> saqAnswers = getChoices();
        for (Choice answer : saqAnswers) {
            if (hasAllKeywords(choiceToCheck, answer)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasAllKeywords(Choice choiceToCheck, Choice answer) {
        String input = choiceToCheck.getTitle(); // user input is passed as a selected choice
        if (containsAllKeywords(input, answer.getKeywords())) {
            getStatistic().addCorrect();
            return true;
        }
        return false;
    }

    private boolean containsAllKeywords(String input, Set<String> keywords) {
        for (String keyword : keywords) {
            if (!input.toLowerCase().contains(keyword)) { // keywords are already stored in lowercase
                return false;
            }
        }
        return true;
    }

    public String getKeywordsFormattedString() {
        Set<Choice> choices = getChoices();
        assert choices.size() == 1 : "SAQ should have exactly one choice";
        for (Choice choice : choices) {
            return choice.getKeywordsString();
        }
        return "";
    }

    @Override
    public int getQuestionType() {
        return SAQ_QUESTION_TYPE;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ShortAnswerQuestion)) {
            return false;
        }

        return super.equals(other);
    }
}

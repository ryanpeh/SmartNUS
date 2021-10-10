package seedu.address.testutil;

import java.util.ArrayList;

import seedu.address.model.question.Question;
import seedu.address.model.quiz.Quiz;

public class TypicalQuiz {

    public static final Quiz DEFAULT_QUIZ = new QuizBuilder().build();
    public static final Quiz SEVEN_QUESTIONS_QUIZ = new QuizBuilder(TypicalQuestions.getTypicalQuestions())
            .build();
    public static final Quiz FIVE_QUESTIONS_QUIZ = new QuizBuilder(
            new ArrayList<Question>() {
                {
                    add(TypicalQuestions.ALICE);
                    add(TypicalQuestions.BENSON);
                    add(TypicalQuestions.CARL);
                    add(TypicalQuestions.DANIEL);
                    add(TypicalQuestions.ELLE);
                }
            }
    ).build();

    private TypicalQuiz() {} // Prevents instantiation

}

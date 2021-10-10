package seedu.address.model.quiz;

import seedu.address.model.question.Question;
import seedu.address.model.quiz.exceptions.QuizOutOfBoundException;

/**
 * The API for the Quiz component.
 */
public interface Quiz {
    /**
     * Gets the current question.
     * @return The current question.
     */
    public Question currQuestion();

    /**
     * Gets the next question.
     * @return The question after the current question.
     * @throws QuizOutOfBoundException when navigation goes out of bound.
     */
    public Question nextQuestion() throws QuizOutOfBoundException;

    /**
     * Gets the previous question.
     * @return The question before the current question.
     * @throws QuizOutOfBoundException when navigation goes out of bound.
     */
    public Question prevQuestion() throws QuizOutOfBoundException;
}

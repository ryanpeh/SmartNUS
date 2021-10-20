package seedu.smartnus.model.quiz.exceptions;

/**
 * Signals that the navigation operatibvon will result in an out of bound error.
 */
public class QuizOutOfBoundException extends RuntimeException {
    public QuizOutOfBoundException() {
        super("Cannot navigate there! This is the edge of the quiz.");
    }
}

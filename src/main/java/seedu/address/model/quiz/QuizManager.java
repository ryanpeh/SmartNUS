package seedu.address.model.quiz;

import seedu.address.model.AddressBook;
import seedu.address.model.ModelManager;
import seedu.address.model.question.Question;
import seedu.address.model.quiz.exceptions.QuizOutOfBoundException;

import java.util.List;


/**
 * The quiz manager of the app.
 */
public class QuizManager implements Quiz {

    private final List<Question> questions;
    private int currentIndex;
    private final int totalQuestions;

    /**
     * Creates a quiz manager with a given list of questions.
     * @param questions The list of Questions in the quiz.
     */
    public QuizManager(List<Question> questions) {
        this.questions = questions;
        this.currentIndex = 0;
        this.totalQuestions = questions.size();
    }

    @Override
    public Question currQuestion() {
        return questions.get(currentIndex);
    }

    @Override
    public Question nextQuestion() throws QuizOutOfBoundException {
        int index = currentIndex + 1;
        if (isValidIndex(index)) {
            currentIndex++;
            return currQuestion();
        }
        throw new QuizOutOfBoundException();
    }

    @Override
    public Question prevQuestion() throws QuizOutOfBoundException{
        int index = currentIndex - 1;
        if (isValidIndex(index)) {
            currentIndex--;
            return currQuestion();
        }
        throw new QuizOutOfBoundException();
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < totalQuestions;
    }

}

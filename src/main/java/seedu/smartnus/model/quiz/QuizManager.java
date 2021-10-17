package seedu.smartnus.model.quiz;

import java.util.List;

import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.question.Statistic;
import seedu.smartnus.model.quiz.exceptions.QuizOutOfBoundException;



/**
 * The quiz manager of the app.
 */
public class QuizManager implements Quiz {

    private final List<Question> questions;
    private int currentIndex;
    private final int totalQuestions;
    private final Statistic statistic;

    /**
     * Creates a quiz manager with a given list of questions.
     * @param questions The list of Questions in the quiz.
     */
    public QuizManager(List<Question> questions) {
        this.questions = questions;
        this.currentIndex = 0;
        this.totalQuestions = questions.size();
        this.statistic = new Statistic();
        this.shuffleQuestionChoices();
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
    public Question prevQuestion() throws QuizOutOfBoundException {
        int index = currentIndex - 1;
        if (isValidIndex(index)) {
            currentIndex--;
            return currQuestion();
        }
        throw new QuizOutOfBoundException();
    }

    @Override
    public Statistic getStatistic() {
        return statistic;
    }

    @Override
    public boolean attemptAndCheckAnswer(Choice choice) {
        statistic.addAttempt();
        if (currQuestion().attemptAndCheckAnswer(choice)) {
            statistic.addCorrect();
            return true;
        }
        return false;
    }

    /**
     * Shuffles the order of the choices in each question.
     */
    private void shuffleQuestionChoices() {
        for (Question question : questions) {
            question.shuffleChoices();
        }
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < totalQuestions;
    }

}

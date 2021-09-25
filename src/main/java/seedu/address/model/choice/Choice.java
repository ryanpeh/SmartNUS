package seedu.address.model.choice;

public class Choice {
    public final String title;
    public final boolean isCorrect;
    
    public Choice(String title, boolean isCorrect) {
        this.title = title;
        this.isCorrect = isCorrect;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }
}

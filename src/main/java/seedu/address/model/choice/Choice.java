package seedu.address.model.choice;


public class Choice {
    private final String title;
    private final boolean isCorrect;
    
    public Choice(String title, boolean isCorrect) {
        this.title = title;
        this.isCorrect = isCorrect;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }
    
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        
        if (!(other instanceof Choice)) {
            return false;
        }

        Choice otherChoice = (Choice) other;
        return otherChoice.getTitle().equals(getTitle())
                && otherChoice.getIsCorrect() == getIsCorrect();
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}

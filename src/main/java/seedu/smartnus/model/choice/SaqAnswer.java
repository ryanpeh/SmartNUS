package seedu.smartnus.model.choice;

import java.util.Set;

import static java.util.Objects.requireNonNull;

public class SaqAnswer extends Choice {
    public static final String MESSAGE_KEYWORD_CONSTRAINTS =
            "Keywords can take any values, and they should not be blank";
    
    private Set<String> keywords;
    
    public SaqAnswer(String title, boolean isCorrect, Set<String> keywords) {
        super(title, isCorrect);
        
        requireNonNull(keywords);
        this.keywords = keywords;
    }
}

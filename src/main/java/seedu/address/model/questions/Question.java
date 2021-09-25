package seedu.address.model.questions;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.choice.Choice;
import seedu.address.model.tag.Tag;

public abstract class Question {
    // Identity fields
    private int id;
    private String title;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    public Question(int id, String title, Set<Tag> tags) {
        requireAllNonNull(id, title, tags);
        this.id = id;
        this.title = title;
        this.tags.addAll(tags);
    }

    // Getters
    public int getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }
    
    /**
     * Checks if a given {@code Choice} is the correct answer to the {@code Question}.
     */
    public abstract boolean checkAnswer(Choice choice);
    
    /**
     * Returns true if the {@code Question} is valid and false otherwise. Conditions for validity can depend on {@code Question} types.
     */
    public abstract boolean isValidQuestion();
}

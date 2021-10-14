package seedu.address.model.question;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Question}'s {@code Name} matches any of the keywords given.
 */
public class TagsContainKeywordsPredicate implements Predicate<Question> {
    private final List<String> keywords;

    public TagsContainKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Question question) {
        Set<Tag> tags = question.getTags();
        return keywords.stream()
                .anyMatch(keyword -> matchTagsIgnoreCase(tags, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagsContainKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagsContainKeywordsPredicate) other).keywords)); // state check
    }
    
    private boolean matchTagsIgnoreCase(Set<Tag> tags, String keyword) {
        return tags.stream()
                .anyMatch(tag -> StringUtil.containsWordIgnoreCase(tag.getTagName(), keyword));
    }

}

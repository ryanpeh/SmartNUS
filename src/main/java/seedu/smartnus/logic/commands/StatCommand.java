package seedu.smartnus.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.smartnus.logic.commands.CommandUtil.TAG_KEYWORD;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.function.Predicate;

import seedu.smartnus.model.Model;
import seedu.smartnus.model.statistic.TagStatistic;
import seedu.smartnus.model.statistic.predicates.ShowAllStatsPredicate;

public class StatCommand extends Command {

    public static final String COMMAND_WORD = "stat";
    public static final String MESSAGE_FOUND_STATS_FORMAT = "%1$d stats found!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds the statistics for a tag (case-insensitive). "
            + "Parameters: "
            + " [" + PREFIX_TAG + "TAG]..."
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAG + "CS2040S";

    private final ArrayList<Predicate<TagStatistic>> predicates = new ArrayList<>();

    public StatCommand(ArrayList<Predicate<TagStatistic>> filterPredicates) {
        predicates.addAll(filterPredicates);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredTagStatistic(combinePredicates());
        model.setPanel(TAG_KEYWORD);
        return new CommandResult(String.format(MESSAGE_FOUND_STATS_FORMAT, model.getTagStatistic().size()));
    }

    private Predicate<TagStatistic> combinePredicates() {
        Predicate<TagStatistic> combinedPredicates = new ShowAllStatsPredicate();
        for (Predicate<TagStatistic> predicate : predicates) {
            combinedPredicates = combinedPredicates.and(predicate);
        }
        return combinedPredicates;
    }

}

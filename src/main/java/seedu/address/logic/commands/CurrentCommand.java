package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_WORKOUTS;

import java.util.*;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import static seedu.address.logic.parser.ParserUtil.parseTag;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.workout.*;

/**
 * Edits the details of an existing workout in the workout book.
 */
public class CurrentCommand extends Command {

    public static final String COMMAND_WORD = "current";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets a workout to be the current workout identified "
            + "by the index number used in the displayed workout list. "
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_CURRENT_WORKOUT_SUCCESS = "Current Workout: %1$s";
    public static final String MESSAGE_DUPLICATE_CURRENT_WORKOUT = "This workout is already the current one.";

    private final Index targetIndex;

    /**
     * @param targetIndex of the person in the filtered workout list to edit the future tag
     */
    public CurrentCommand(Index targetIndex) {
        requireNonNull(targetIndex);

        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        List<Workout> filteredWorkoutList = model.getFilteredWorkoutList();
        Workout workoutToEdit = filteredWorkoutList.get(targetIndex.getZeroBased());
        Workout editedWorkout = createEditedWorkout(workoutToEdit);

        if (targetIndex.getZeroBased() >= filteredWorkoutList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_WORKOUT_DISPLAYED_INDEX);
        }

        model.updateWorkout(workoutToEdit, editedWorkout);
        model.updateFilteredWorkoutList(PREDICATE_SHOW_ALL_WORKOUTS);
        model.commitWorkoutBook();
        return new CommandResult(String.format(MESSAGE_CURRENT_WORKOUT_SUCCESS, editedWorkout));
    }

    /**
     * Creates and returns a {@code Workout} with the details of {@code workoutToEdit}
     * edited with {@code editWorkoutDescriptor}.
     */
    private static Workout createEditedWorkout(Workout workoutToEdit) {
        assert workoutToEdit != null;

        Name updatedName = workoutToEdit.getName();
        Type updatedType = workoutToEdit.getType();
        Duration updatedDuration = workoutToEdit.getDuration();
        Difficulty updatedDifficulty = workoutToEdit.getDifficulty();
        Equipment updatedEquipment = workoutToEdit.getEquipment();
        Muscle updatedMuscle = workoutToEdit.getMuscle();
        Calories updatedCalories = workoutToEdit.getCalories();
        Instruction updatedInstruction = workoutToEdit.getInstruction();
        Set<Tag> originalTags = workoutToEdit.getTags();
        Set<Tag> updatedTags = new HashSet<>();
        for (Tag entry: originalTags) {
            updatedTags.add(entry);
        }
        try {
            Tag f = parseTag("future");
            Tag c = parseTag("current");

            if (originalTags.contains(c)) {
                throw new CommandException(MESSAGE_DUPLICATE_CURRENT_WORKOUT);
            }
            updatedTags.remove(f);
            updatedTags.add(c);
        } catch (CommandException | ParseException e1) {
            e1.printStackTrace();
        }

        return new Workout(updatedName, updatedType, updatedDuration, updatedDifficulty, updatedEquipment,
                updatedMuscle, updatedCalories, updatedInstruction, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CurrentCommand)) {
            return false;
        }

        // state check
        CurrentCommand e = (CurrentCommand) other;
        return targetIndex.equals(e.targetIndex);
    }
}
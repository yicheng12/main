package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.ReadOnlyTrackedData;
import seedu.address.model.ReadOnlyTrackedDataList;
import seedu.address.model.ReadOnlyWorkoutBook;
import seedu.address.model.TrackedData;
import seedu.address.model.TrackedDataList;
import seedu.address.model.WorkoutBook;
import seedu.address.model.tag.Tag;
import seedu.address.model.workout.Calories;
import seedu.address.model.workout.Difficulty;
import seedu.address.model.workout.Duration;
import seedu.address.model.workout.Equipment;
import seedu.address.model.workout.Instruction;
import seedu.address.model.workout.Muscle;
import seedu.address.model.workout.Name;
import seedu.address.model.workout.Parameter;
import seedu.address.model.workout.Remark;
import seedu.address.model.workout.Type;
import seedu.address.model.workout.Workout;

/**
 * Contains utility methods for populating {@code WorkoutBook} with sample data.
 */
public class SampleDataUtil {
    public static Workout[] getSampleWorkouts() {
        return new Workout[] {
            new Workout(new Name("alex yeoh's workout"), new Type("strength"), new Duration("20m"),
                    new Difficulty("beginner"), new Equipment("dumbbell"), new Muscle("tricep"),
                    new Calories("150"), new Instruction("set1: tricep extension reps: 4-6"),
                    getTagSet("future", "heavy", "morning"),
                    new Remark("This is a great upper body workout, targeting the muscles in arms,"
                            + " the shoulders, the center and your back.")
            ),
            new Workout(new Name("commando workout"), new Type("strength, cardio"), new Duration("20m"),
                    new Difficulty("advanced"), new Equipment("dumbbell, bench"), new Muscle("triceps"),
                    new Calories("150"),
                    new Instruction("set 1: bicep curl reps: 4-6 set 2: tricep extension reps: 4-6"),
                    getTagSet("future", "heavy"), new Remark("")
            ),
            new Workout(new Name("5BX warm up"), new Type("warm up"), new Duration("10m"),
                    new Difficulty("beginner"), new Equipment("NIL"), new Muscle("everything"),
                    new Calories("100"), new Instruction("Jumping Jack 5 Counts of 4, Squat Bender 5 Counts of 4"
                    + "High Jumper 5 Counts of 4, Crunches 5 Counts of 4 and Push ups 5 Counts of 4"),
                    getTagSet("future", "army", "NSdailylife"), new Remark("")
            ),
            new Workout(new Name("long distance run"), new Type("endurance, aerobic"), new Duration("30m"),
                    new Difficulty("beginner"), new Equipment("NIL"), new Muscle("leg muscle"),
                    new Calories("225"),
                    new Instruction("run for 30 minutes without walking at your natural pace."),
                    getTagSet("future", "heavy"), new Remark("")
            ),
            new Workout(new Name("brisk walk"), new Type("endurance"), new Duration("20m"),
                    new Difficulty("beginner"), new Equipment("NIL"), new Muscle("leg muscle"),
                    new Calories("100"), new Instruction("20 minutes brisk walk"),
                    getTagSet("future", "heavy"), new Remark("")
            ),
            new Workout(new Name("interval training"), new Type("endurance, agility"), new Duration("20m"),
                    new Difficulty("advanced"), new Equipment("NIL"), new Muscle("leg muscle"),
                    new Calories("250"), new Instruction("Run around the track(400m) for about 1m 40s and rest for 1m, "
                    + "repeat 8 times"),
                    getTagSet("future", "heavy"), new Remark("")
            ),
            new Workout(new Name("circuit training"), new Type("endurance"), new Duration("15m"),
                    new Difficulty("advanced"), new Equipment("NIL"), new Muscle("everything"),
                    new Calories("250"), new Instruction("Each set consists of 10 of the following exercise: burpees,"
                    + " push ups, squats and high jump. Do this set 3 times with minimal rest."),
                    getTagSet("future", "tiring"), new Remark("")
            )
        };
    }

    public static ReadOnlyWorkoutBook getSampleWorkoutBook() {
        WorkoutBook sampleWb = new WorkoutBook();
        for (Workout sampleWorkout : getSampleWorkouts()) {
            sampleWb.addWorkout(sampleWorkout);
        }
        return sampleWb;
    }

    public static Parameter[] getSampleParameters() {
        return new Parameter[] {
            new Parameter(new Prefix("name/"), "commando"),
            new Parameter(new Prefix("duration/"), "20m"),
            new Parameter(new Prefix("difficulty/"), "advanced"),
            new Parameter(new Prefix("equipment/"), "dumbbell"),
            new Parameter(new Prefix("calories/"), "150")
        };
    }

    public static ReadOnlyTrackedDataList getSampleTrackedDataList() {
        TrackedDataList sampleTdl = new TrackedDataList();
        for (Parameter sampleParameter : getSampleParameters()) {
            sampleTdl.addParameter(sampleParameter);
        }
        return sampleTdl;
    }

    public static ReadOnlyTrackedData getEmptyTrackedData() {
        TrackedData emptyTrackedData = new TrackedData();
        return emptyTrackedData;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}

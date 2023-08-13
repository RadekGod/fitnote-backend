package pl.fitnote.exerciseSet;

import org.springframework.stereotype.Service;
import pl.fitnote.exercise.Exercise;
import pl.fitnote.trainingPlan.TrainingPlan;

@Service
class ExerciseSetFactory {

    public ExerciseSet createExerciseSet(ExerciseSetDto source, Exercise exercise, TrainingPlan trainingPlan) {
            return ExerciseSet.builder()
                    .weight(source.getWeight())
                    .measureUnit(source.getMeasureUnit())
                    .repeats(source.getRepeats())
                    .completed(source.getCompleted())
                    .note(source.getNote())
                    .exercise(exercise)
                    .trainingPlan(trainingPlan)
                    .build();
    }

    public ExerciseSet updateExerciseSet(ExerciseSet exerciseSetToUpdate, ExerciseSetDto source, Exercise exercise) {
        return exerciseSetToUpdate.toBuilder()
                .weight(source.getWeight())
                .measureUnit(source.getMeasureUnit())
                .repeats(source.getRepeats())
                .completed(source.getCompleted())
                .note(source.getNote())
                .exercise(exercise)
                .build();
    }
}

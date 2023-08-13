package pl.fitnote.trainingPlan;

import org.springframework.stereotype.Service;
import pl.fitnote.exercise.Exercise;

@Service
class TrainingPlanExerciseFactory {

    TrainingPlanExercise createTrainingPlanExercise(TrainingPlanExerciseDto source, Exercise exercise, TrainingPlan trainingPlan) {
            return TrainingPlanExercise.builder()
                    .measureUnit(source.getMeasureUnit())
                    .note(source.getNote())
                    .exercise(exercise)
                    .trainingPlan(trainingPlan)
                    .build();
    }

    ExerciseSet createExerciseSet(ExerciseSetDto source, TrainingPlanExercise trainingPlanExercise) {
        return ExerciseSet.builder()
                .weight(source.getWeight())
                .repeats(source.getRepeats())
                .completed(source.getCompleted())
                .note(source.getNote())
                .trainingPlanExercise(trainingPlanExercise)
                .build();
    }

    ExerciseSet updateExerciseSet(ExerciseSet exerciseSetToUpdate, ExerciseSetDto source) {
        return exerciseSetToUpdate.toBuilder()
                .weight(source.getWeight())
                .repeats(source.getRepeats())
                .completed(source.getCompleted())
                .note(source.getNote())
                .build();
    }
}

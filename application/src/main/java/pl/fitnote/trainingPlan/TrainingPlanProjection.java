package pl.fitnote.trainingPlan;

import pl.fitnote.exerciseSet.ExerciseSetProjection;
import pl.fitnote.user.dto.UserProjection;

import java.util.List;

public interface TrainingPlanProjection {
    Long getId();
    String getName();
    List<TrainingDay> getTrainingDays();
    String getNote();
    List<ExerciseSetProjection> getExerciseSets();
    UserProjection getUser();
}

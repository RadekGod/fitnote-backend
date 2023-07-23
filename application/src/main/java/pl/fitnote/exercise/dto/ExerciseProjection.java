package pl.fitnote.exercise.dto;

import pl.fitnote.exercise.ExerciseCategoryGroup;
import pl.fitnote.exercise.ExerciseType;
import pl.fitnote.exercise.InvolvedMuscles;
import pl.fitnote.user.dto.UserProjection;

import java.util.List;

public interface ExerciseProjection {
    Long getId();
    String getName();
    String getDescription();
    List<InvolvedMuscles> getMainMuscles();
    List<InvolvedMuscles> getSupportiveMuscles();
    List<ExerciseCategoryGroup> getExerciseCategoryGroups();
    ExerciseType getExerciseType();
    UserProjection getUser();
}

package pl.fitnote.exercise;

import pl.fitnote.commons.file.SimpleApplicationFileProjection;

import java.util.List;
import java.util.Set;

public interface SimpleExerciseProjection {
    Long getId();
    String getName();
    String getDescription();
    List<InvolvedMuscles> getMainMuscles();
    List<InvolvedMuscles> getSupportiveMuscles();
    Boolean getCustom();
    Set<ExerciseCategoryGroupProjection> getExerciseCategoryGroups();
    ExerciseType getExerciseType();
    SimpleApplicationFileProjection getApplicationFile();
}

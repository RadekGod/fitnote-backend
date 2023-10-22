package pl.fitnote.exercise;

import pl.fitnote.commons.file.ApplicationFileProjection;

import java.util.List;
import java.util.Set;

public interface ExerciseProjection {
    Long getId();

    String getName();

    String getDescription();

    List<InvolvedMuscles> getMainMuscles();

    List<InvolvedMuscles> getSupportiveMuscles();

    Boolean getCustom();

    Set<ExerciseCategoryGroupProjection> getExerciseCategoryGroups();

    ExerciseType getExerciseType();

    ApplicationFileProjection getApplicationFile();
}

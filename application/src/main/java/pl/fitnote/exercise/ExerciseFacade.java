package pl.fitnote.exercise;

import pl.fitnote.commons.UserDetails;
import pl.fitnote.exercise.dto.ExerciseProjection;

import java.util.List;

interface ExerciseFacade {
    Long createExercise(ExerciseDto command, UserDetails userDetails);
    List<ExerciseProjection> getAllExercises(UserDetails userDetails);
    ExerciseProjection getExercise(Long exerciseId, UserDetails userDetails);
    void updateExercise(ExerciseDto command, UserDetails userDetails);
    void deleteExercise(Long exerciseId, UserDetails userDetails);
}

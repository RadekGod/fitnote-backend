package pl.fitnote.exercise;

import pl.fitnote.commons.UserDetails;
import pl.fitnote.exercise.dto.ExerciseProjection;

import java.util.List;

public interface ExerciseFacade {
    Long createExercise(ExerciseDto command, UserDetails userDetails);
    List<ExerciseProjection> getAllExercises(UserDetails userDetails);
    <T> T getExercise(Long exerciseId, UserDetails userDetails, Class<T> type);
    void updateExercise(Long exerciseId, ExerciseDto command, UserDetails userDetails);
    void deleteExercise(Long exerciseId, UserDetails userDetails);
}

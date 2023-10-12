package pl.fitnote.exercise;

import org.springframework.web.multipart.MultipartFile;
import pl.fitnote.commons.UserDetails;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ExerciseFacade {
    Long createExercise(Optional<MultipartFile> image, ExerciseDto command, UserDetails userDetails) throws IOException;
    List<ExerciseProjection> getAllExercises(UserDetails userDetails);
    List<ExerciseProjection> getAllExercisesFromCategory(ExerciseCategoryGroupEnum exerciseCategoryGroupEnum, UserDetails userDetails);
    <T> T getExercise(Long exerciseId, UserDetails userDetails, Class<T> type);
    void updateExercise(Long exerciseId, ExerciseDto command, UserDetails userDetails);
    void deleteExercise(Long exerciseId, UserDetails userDetails);
}

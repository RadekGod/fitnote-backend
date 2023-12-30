package pl.fitnote.training;

import java.time.LocalDateTime;
import java.util.List;

public interface TrainingProjection {
    Long getId();
    String getName();
    LocalDateTime getStartTime();
    LocalDateTime getFinishTime();
    List<TrainingExerciseProjection> getTrainingExercises();
}

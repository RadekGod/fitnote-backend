package pl.fitnote.training;

import java.time.LocalDateTime;
import java.util.List;

public interface SimpleTrainingProjection {
    Long getId();

    String getName();

    String getNote();

    LocalDateTime getStartTime();

    LocalDateTime getFinishTime();

    List<SimpleTrainingExerciseProjection> getTrainingExercises();
}

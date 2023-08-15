package pl.fitnote.training;

import pl.fitnote.user.dto.UserProjection;

import java.time.LocalDateTime;
import java.util.List;

public interface TrainingProjection {
    Long getId();
    String getName();
    String getNote();
    LocalDateTime getStartTime();
    LocalDateTime getFinishTime();
    List<TrainingExerciseProjection> getTrainingExercises();
    UserProjection getUser();
}

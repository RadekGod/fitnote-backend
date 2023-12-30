package pl.fitnote.training;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainingDto {
    private Long id;
    private String name;
    private List<TrainingExerciseDto> trainingExercises;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
}

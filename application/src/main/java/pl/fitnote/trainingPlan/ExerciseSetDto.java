package pl.fitnote.trainingPlan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExerciseSetDto {
    private Long id;
    private Float weight;
    private Long repeats;
    private Boolean completed;
    private String note;
    private TrainingPlanExerciseDto trainingPlanExerciseDto;
}

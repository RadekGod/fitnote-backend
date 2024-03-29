package pl.fitnote.trainingPlan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.fitnote.exercise.ExerciseDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainingPlanExerciseDto {
    private Long id;
    private MeasurementUnit measurementUnit;
    private String note;
    private ExerciseDto exercise;
    private List<ExerciseSetDto> exerciseSets;
    private TrainingPlanDto trainingPlanDto;
}

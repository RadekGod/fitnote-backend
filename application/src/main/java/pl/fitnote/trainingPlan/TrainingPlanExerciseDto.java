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
    private MeasureUnit measureUnit;
    private String note;
    private ExerciseDto exerciseDto;
    private List<ExerciseSetDto> exerciseSetDtoList;
    private TrainingPlanDto trainingPlanDto;
}

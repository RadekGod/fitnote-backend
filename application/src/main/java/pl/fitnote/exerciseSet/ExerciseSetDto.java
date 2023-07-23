package pl.fitnote.exerciseSet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.fitnote.exercise.ExerciseDto;
import pl.fitnote.trainingPlan.TrainingPlanDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExerciseSetDto {

    private Float weight;
    private MeasureUnit measureUnit;
    private Long repeats;
    private Boolean completed;
    private String note;
    private ExerciseDto exercise;
    private TrainingPlanDto trainingPlan;
}

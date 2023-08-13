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
    private Long id;
    private Float weight;
    private MeasureUnit measureUnit;
    private Long repeats;
    private Boolean completed;
    private String note;
    private ExerciseDto exercise;
    private TrainingPlanDto trainingPlan;

    public static ExerciseSet toEntity(ExerciseSetDto source) {
        return ExerciseSet.builder()
        .weight(source.getWeight())
        .measureUnit(source.getMeasureUnit())
        .repeats(source.getRepeats())
        .completed(source.getCompleted())
        .note(source.getNote())
//        .exercise(getExercise())
//        .trainingPlan(source.getTrainingPlan())
        .build();
    }
}

package pl.fitnote.training;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.fitnote.exercise.ExerciseDto;
import pl.fitnote.trainingPlan.MeasureUnit;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
class TrainingExerciseDto {
    private Long id;
    private MeasureUnit measureUnit;
//    private Long exerciseSequenceNumber;
    private String note;
    private ExerciseDto exercise;
    private List<TrainingExerciseSetDto> exerciseSets;
}

package pl.fitnote.trainingPlan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.fitnote.exerciseSet.ExerciseSetDto;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainingPlanDto {
        private Long id;
        private String name;
        private List<ExerciseSetDto> exerciseSets;
        private List<TrainingDay> trainingDays;
        private String note;
}

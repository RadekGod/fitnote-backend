package pl.fitnote.trainingPlan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainingPlanDto {
        private Long id;
        private String name;
        private List<TrainingDay> trainingDays;
        private List<TrainingPlanExerciseDto> trainingPlanExercises;
}

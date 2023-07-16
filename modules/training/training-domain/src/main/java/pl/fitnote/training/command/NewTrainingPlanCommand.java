package pl.fitnote.training.command;

import pl.fitnote.training.model.TrainingPlanExercise;
import pl.fitnote.training.model.vo.TrainingPlanDay;

import java.util.List;

public record NewTrainingPlanCommand(
        String trainingPlanName,
        List<TrainingPlanExercise> exercises,
        List<TrainingPlanDay> trainingPlanDays,
        String note
) {
}

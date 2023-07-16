package pl.fitnote.training.command;

import pl.fitnote.training.model.TrainingPlanExercise;
import pl.fitnote.training.model.vo.TrainingPlanDay;
import pl.fitnote.training.model.vo.TrainingPlanName;
import pl.fitnote.training.model.vo.TrainingPlanNote;

import java.util.List;

public record UpdateTrainingPlanCommand(
        TrainingPlanName trainingPlanName,
        List<TrainingPlanExercise> exercises,
        List<TrainingPlanDay> trainingPlanDays,
        TrainingPlanNote note
) {
}

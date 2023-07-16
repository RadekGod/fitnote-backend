package pl.fitnote.training.model;

import pl.fitnote.training.model.vo.ExerciseSequenceNumber;
import pl.fitnote.training.model.vo.TrainingPlanExerciseId;

import java.util.List;

public class TrainingPlanExercise {
    private TrainingPlanExerciseId id;
    private List<ExerciseSet> exerciseSet;
    private ExerciseSequenceNumber exerciseSequenceNumber;
}

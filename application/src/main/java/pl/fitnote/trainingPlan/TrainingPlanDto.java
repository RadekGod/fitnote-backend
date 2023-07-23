package pl.fitnote.trainingPlan;

import pl.fitnote.exerciseSet.ExerciseSet;

import java.util.List;

public class TrainingPlanDto {
        private String name;
        private List<ExerciseSet> exerciseSets;
        private List<TrainingDay> trainingDays;
        private String note;
}

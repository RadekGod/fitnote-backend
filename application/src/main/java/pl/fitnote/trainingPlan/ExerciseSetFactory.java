package pl.fitnote.trainingPlan;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ExerciseSetFactory {

    public List<ExerciseSet> createExerciseSet(List<ExerciseSetDto> source, TrainingPlanExercise trainingPlanExercise) {
        return source.stream().map(exerciseSetDto -> ExerciseSet.builder()
                .weight(exerciseSetDto.getWeight())
                .repeats(exerciseSetDto.getRepeats())
                .completed(exerciseSetDto.getCompleted())
                .note(exerciseSetDto.getNote())
                .trainingPlanExercise(trainingPlanExercise)
                .build()).toList();
    }
}

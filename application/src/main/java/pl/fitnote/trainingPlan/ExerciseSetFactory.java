package pl.fitnote.trainingPlan;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ExerciseSetFactory {

    public List<ExerciseSet> createExerciseSet(List<ExerciseSetDto> source, TrainingPlanExercise trainingPlanExercise) {
        return source.stream().map(exerciseSetDto -> ExerciseSet.builder()
                .weight(exerciseSetDto.getWeight())
                .repeats(exerciseSetDto.getRepeats())
                .note(exerciseSetDto.getNote())
                .trainingPlanExercise(trainingPlanExercise)
                .build()).toList();
    }

    ExerciseSetDto createExerciseSetDtoFromProjection(ExerciseSetProjection source) {
        return ExerciseSetDto.builder()
                .id(source.getId())
                .weight(source.getWeight())
                .repeats(source.getRepeats())
                .note(source.getNote())
                .build();
    }
}

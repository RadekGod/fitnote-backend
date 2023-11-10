package pl.fitnote.training;

import org.springframework.stereotype.Service;
import pl.fitnote.trainingPlan.ExerciseSet;
import pl.fitnote.trainingPlan.ExerciseSetDto;
import pl.fitnote.trainingPlan.ExerciseSetProjection;
import pl.fitnote.trainingPlan.TrainingPlanExercise;

import java.util.List;

@Service
public
class TrainingExerciseSetFactory {

    public List<ExerciseSet> createExerciseSet(List<ExerciseSetDto> source, TrainingPlanExercise trainingPlanExercise) {
        return source.stream().map(exerciseSetDto -> ExerciseSet.builder()
                .weight(exerciseSetDto.getWeight())
                .repeats(exerciseSetDto.getRepeats())
                .completed(exerciseSetDto.getCompleted())
                .note(exerciseSetDto.getNote())
                .trainingPlanExercise(trainingPlanExercise)
                .build()).toList();
    }

    TrainingExerciseSetDto createExerciseSetDtoFromProjection(TrainingExerciseSetProjection source) {
        return TrainingExerciseSetDto.builder()
                .id(source.getId())
                .weight(source.getWeight())
                .repeats(source.getRepeats())
                .completed(source.getCompleted())
                .note(source.getNote())
                .build();
    }
}

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

     List<TrainingExerciseSet> createExerciseSet(List<TrainingExerciseSetDto> source, TrainingExercise trainingExercise) {
        return source.stream().map(exerciseSetDto -> TrainingExerciseSet.builder()
                .weight(exerciseSetDto.getWeight())
                .repeats(exerciseSetDto.getRepeats())
                .completed(exerciseSetDto.getCompleted())
                .note(exerciseSetDto.getNote())
                .trainingExercise(trainingExercise)
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

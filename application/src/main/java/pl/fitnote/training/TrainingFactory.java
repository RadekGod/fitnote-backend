package pl.fitnote.training;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.fitnote.exercise.Exercise;
import pl.fitnote.exercise.ExerciseFactory;
import pl.fitnote.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;


@Service
@RequiredArgsConstructor
class TrainingFactory {

    private final ExerciseFactory exerciseFactory;
    private final TrainingExerciseSetFactory trainingExerciseSetFactory;
    Training createTrainingFromDto(TrainingDto source, List<TrainingExercise> trainingExercises, User requestingUser) {
        return Training.builder()
                .name(source.getName())
                .startTime(source.getStartTime())
                .finishTime(source.getFinishTime())
                .trainingExercises(trainingExercises)
                .user(requestingUser)
                .build();
    }

    TrainingExerciseSet createTrainingExerciseSet(TrainingExerciseSetDto source) {
        return TrainingExerciseSet.builder()
                .weight(source.getWeight())
                .repeats(source.getRepeats())
                .completed(source.getCompleted())
                .note(source.getNote())
                .build();
    }

    TrainingExerciseSet createTrainingExerciseSetForExistingTrainingExercise(TrainingExerciseSetDto source, TrainingExercise trainingExercise) {
        return TrainingExerciseSet.builder()
                .weight(source.getWeight())
                .repeats(source.getRepeats())
                .completed(source.getCompleted())
                .note(source.getNote())
                .trainingExercise(trainingExercise)
                .build();
    }

    TrainingExerciseSet updateTrainingExerciseSet(TrainingExerciseSet trainingExerciseSet, TrainingExerciseSetDto source) {
        return trainingExerciseSet.toBuilder()
                .weight(source.getWeight())
                .repeats(source.getRepeats())
                .completed(source.getCompleted())
                .note(source.getNote())
                .build();
    }

    TrainingExercise createTrainingExercise(TrainingExerciseDto source, Exercise exercise, List<TrainingExerciseSet> trainingExerciseSets) {
        return TrainingExercise.builder()
                .measurementUnit(source.getMeasurementUnit())
                .note(source.getNote())
                .exercise(exercise)
                .exerciseSets(trainingExerciseSets)
                .build();
    }

    static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    TrainingDto createTrainingDtoFromProjection(SimpleTrainingProjection source) {
        List<TrainingExerciseDto> trainingPlanExerciseDtoList = new ArrayList<>();
        source.getTrainingExercises().stream().filter(distinctByKey(SimpleTrainingExerciseProjection::getId)).forEach(trainingExerciseProjection -> {
            trainingPlanExerciseDtoList.add(createTrainingExerciseDtoFromProjection(trainingExerciseProjection));
        });
        return TrainingDto.builder()
                .id(source.getId())
                .name(source.getName())
                .trainingExercises(trainingPlanExerciseDtoList)
                .startTime(source.getStartTime())
                .finishTime(source.getFinishTime())
                .build();
    }

    private TrainingExerciseDto createTrainingExerciseDtoFromProjection(SimpleTrainingExerciseProjection source) {
        List<TrainingExerciseSetDto> exerciseSetDtoList = new ArrayList<>();
        source.getExerciseSets().forEach(trainingExerciseSetProjection -> {
            exerciseSetDtoList.add(trainingExerciseSetFactory.createExerciseSetDtoFromProjection(trainingExerciseSetProjection));
        });
        return TrainingExerciseDto.builder()
                .id(source.getId())
                .measurementUnit(source.getMeasurementUnit())
                .note(source.getNote())
                .exercise(exerciseFactory.createExerciseDtoFromProjection(source.getExercise()))
                .exerciseSets(exerciseSetDtoList)
                .build();
    }
}

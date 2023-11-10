package pl.fitnote.trainingPlan;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
class TrainingPlanFactory {

    private final ExerciseFactory exerciseFactory;
    private final ExerciseSetFactory exerciseSetFactory;
    TrainingPlan createTrainingPlanFromDto(CreateTrainingPlanDto source, User requestingUser) {
        return TrainingPlan.builder()
                .name(source.getName())
                .trainingDays(source.getTrainingDays())
                .user(requestingUser)
                .build();
    }

    static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    TrainingPlanDto createTrainingPlanDtoFromProjection(SimpleTrainingPlanProjection source) {
        List<TrainingPlanExerciseDto> trainingPlanExerciseDtoList = new ArrayList<>();
        source.getTrainingPlanExercises().stream().filter(distinctByKey(SimpleTrainingPlanExerciseProjection::getId)).forEach(trainingPlanExerciseProjection -> {
            trainingPlanExerciseDtoList.add(createTrainingPlanExerciseDtoFromProjection(trainingPlanExerciseProjection));
        });
        return TrainingPlanDto.builder()
                .id(source.getId())
                .name(source.getName())
                .trainingDays(source.getTrainingDays())
                .trainingPlanExercises(trainingPlanExerciseDtoList)
                .build();
    }

    private TrainingPlanExerciseDto createTrainingPlanExerciseDtoFromProjection(SimpleTrainingPlanExerciseProjection source) {
        List<ExerciseSetDto> exerciseSetDtoList = new ArrayList<>();
        source.getExerciseSets().forEach(exerciseSetProjection -> {
            exerciseSetDtoList.add(exerciseSetFactory.createExerciseSetDtoFromProjection(exerciseSetProjection));
        });
        return TrainingPlanExerciseDto.builder()
                .id(source.getId())
                .measurementUnit(source.getMeasurementUnit())
                .note(source.getNote())
                .exercise(exerciseFactory.createExerciseDtoFromProjection(source.getExercise()))
                .exerciseSets(exerciseSetDtoList)
                .build();
    }



}



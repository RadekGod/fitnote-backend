package pl.fitnote.exercise;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ExerciseFactory {


    Exercise createExerciseFromDto(ExerciseDto source) {
        List<ExerciseCategoryGroup> exerciseCategoryGroups = source.getExerciseCategoryGroups();
        exerciseCategoryGroups.add(ExerciseCategoryGroup.CUSTOM);
        return Exercise.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .mainMuscles(source.getMainMuscles())
                .supportiveMuscles(source.getSupportiveMuscles())
                .exerciseCategoryGroups(exerciseCategoryGroups)
                .exerciseType(source.getExerciseType())
                .build();
    }
}

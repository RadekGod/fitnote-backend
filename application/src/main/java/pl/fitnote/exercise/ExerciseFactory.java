package pl.fitnote.exercise;

import org.springframework.stereotype.Service;

@Service
class ExerciseFactory {

    Exercise createExerciseFromDto(ExerciseDto source) {
        return Exercise.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .mainMuscles(source.getMainMuscles())
                .supportiveMuscles(source.getSupportiveMuscles())
                .custom(true)
                .exerciseType(source.getExerciseType())
                .build();
    }
}

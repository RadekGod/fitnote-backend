package pl.fitnote.exercise;

import org.springframework.stereotype.Service;
import pl.fitnote.commons.file.ApplicationFileDto;
import pl.fitnote.commons.file.SimpleApplicationFileProjection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ExerciseFactory {

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

    public ExerciseDto createExerciseDtoFromProjection(SimpleExerciseProjection source) {
        List<ExerciseCategoryGroupDto> exerciseCategoryGroupDtoList = new ArrayList<>();
        source.getExerciseCategoryGroups().forEach(exerciseCategoryGroupProjection -> {
            exerciseCategoryGroupDtoList.add(createExerciseCategoryGroupDtoFromProjection(exerciseCategoryGroupProjection));
        });
        return ExerciseDto.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .mainMuscles(source.getMainMuscles())
                .supportiveMuscles(source.getSupportiveMuscles())
                .custom(source.getCustom())
                .exerciseCategoryGroups(exerciseCategoryGroupDtoList)
                .exerciseType(source.getExerciseType())
                .applicationFile(createApplicationFileDtoFromProjection(source.getApplicationFile()))
                .build();
    }

    ExerciseCategoryGroupDto createExerciseCategoryGroupDtoFromProjection(ExerciseCategoryGroupProjection source) {
        return ExerciseCategoryGroupDto.builder()
                .id(source.getId())
                .categoryName(source.getCategoryName())
                .build();
    }

    private ApplicationFileDto createApplicationFileDtoFromProjection(SimpleApplicationFileProjection source) {
        if (Objects.isNull(source)) {
            return null;
        }
        return ApplicationFileDto.builder()
                .id(source.getId())
                .fileName(source.getFileName())
                .creationDate(source.getCreationDate())
                .build();
    }
}

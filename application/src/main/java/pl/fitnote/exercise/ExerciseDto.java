package pl.fitnote.exercise;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.fitnote.commons.file.ApplicationFileDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExerciseDto {
    private Long id;
    private String name;
    private String description;
    private List<InvolvedMuscles> mainMuscles;
    private List<InvolvedMuscles> supportiveMuscles;
    private Boolean custom;
    private List<ExerciseCategoryGroupDto> exerciseCategoryGroups;
    private ExerciseType exerciseType;
    private ApplicationFileDto applicationFile;
}

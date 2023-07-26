package pl.fitnote.exercise;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private List<ExerciseCategoryGroup> exerciseCategoryGroups;
    private ExerciseType exerciseType;
}

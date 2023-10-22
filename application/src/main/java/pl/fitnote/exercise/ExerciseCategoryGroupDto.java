package pl.fitnote.exercise;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExerciseCategoryGroupDto {
    private Long id;
    private ExerciseCategoryGroupEnum categoryName;
    private Set<ExerciseDto> exercises;
}

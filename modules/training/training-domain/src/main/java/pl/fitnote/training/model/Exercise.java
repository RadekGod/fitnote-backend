package pl.fitnote.training.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.fitnote.training.model.vo.*;

import java.util.List;

@Getter
@Setter
@Builder
public class Exercise {
    private ExerciseId id;
    private ExerciseName exerciseName;
    private ExerciseType exerciseType;
    private ExerciseCategoryGroup exerciseCategoryGroup;
    private ExerciseDescription description;
    private List<InvolvedMuscles> primarilyInvolvedMuscles;
    private List<InvolvedMuscles> secondarilyInvolvedMuscles;
    private ExerciseNote note;
}

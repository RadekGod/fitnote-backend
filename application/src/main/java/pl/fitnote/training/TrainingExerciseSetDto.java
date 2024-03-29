package pl.fitnote.training;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class TrainingExerciseSetDto {
    private Long id;
    private Float weight;
    private Long repeats;
    private Boolean completed;
    private String note;
}
package pl.fitnote.trainingPlan;


import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.fitnote.exerciseSet.ExerciseSet;

import java.util.List;

@Entity
@Table(name = "training_plan", schema = "training")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainingPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exercise_sequence_generator")
    @SequenceGenerator(name = "exercise_sequence_generator",
            sequenceName = "exercise_id_seq",
            allocationSize = 1,
            schema = "training")
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Convert(converter = TrainingDaysConverter.class)
    private List<TrainingDay> trainingDays;
    private String note;
    @OneToMany(mappedBy = "trainingPlan")
    private List<ExerciseSet> exerciseSets;

}

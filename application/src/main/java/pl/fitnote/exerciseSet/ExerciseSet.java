package pl.fitnote.exerciseSet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.fitnote.exercise.Exercise;
import pl.fitnote.trainingPlan.TrainingPlan;

@Entity
@Table(name = "exercise_set", schema = "training")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ExerciseSet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exercise_set_sequence_generator")
    @SequenceGenerator(name = "exercise_set_sequence_generator",
            sequenceName = "exercise_set_id_seq",
            allocationSize = 1,
            schema = "training")
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;
    private Float weight;
    @Enumerated(EnumType.STRING)
    private MeasureUnit measureUnit;
    private Long repeats;
    private Boolean completed;
    private String note;

    @OneToOne()
    @JoinColumn(nullable = false)
    private Exercise exercise;

    @ManyToOne()
    @JoinColumn(name = "training_plan_id", nullable = false)
    private TrainingPlan trainingPlan;
}

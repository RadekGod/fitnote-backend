package pl.fitnote.trainingPlan;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "exercise_set", schema = "training_plan")
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
            schema = "training_plan")
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;
    private Float weight;
    private Long repeats;
    private String note;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "training_plan_exercise_id", nullable = false)
    private TrainingPlanExercise trainingPlanExercise;
}

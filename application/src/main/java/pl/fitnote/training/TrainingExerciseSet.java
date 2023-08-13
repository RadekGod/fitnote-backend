package pl.fitnote.training;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "training_exercise_set", schema = "training_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
class TrainingExerciseSet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "training_exercise_set_sequence_generator")
    @SequenceGenerator(name = "training_exercise_set_sequence_generator",
            sequenceName = "training_exercise_set_id_seq",
            allocationSize = 1,
            schema = "training_history")
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;
    private Float weight;
    private Long repeats;
    private Boolean completed;
    private String note;

    @ManyToOne()
    @JoinColumn(name = "training_exercise_id", nullable = false)
    private TrainingExercise trainingExercise;
}
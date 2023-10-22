package pl.fitnote.training;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.fitnote.exercise.Exercise;
import pl.fitnote.trainingPlan.MeasurementUnit;

import java.util.List;

@Entity
@Table(name = "training_exercise", schema = "training_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
class TrainingExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "training_exercise_sequence_generator")
    @SequenceGenerator(name = "training_exercise_sequence_generator",
            sequenceName = "training_exercise_id_seq",
            allocationSize = 1,
            schema = "training_history")
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MeasurementUnit measurementUnit;

//    @Column(nullable = false)
//    private Long exerciseSequenceNumber;

    private String note;

    @OneToOne()
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @OneToMany(mappedBy = "trainingExercise", cascade = CascadeType.ALL)
    private List<TrainingExerciseSet> exerciseSets;

    @ManyToOne
    @JoinColumn(name = "training_id", nullable = false)
    private Training training;
}

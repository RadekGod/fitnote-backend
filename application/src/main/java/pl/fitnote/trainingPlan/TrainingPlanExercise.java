package pl.fitnote.trainingPlan;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.fitnote.exercise.Exercise;

import java.util.List;

@Entity
@Table(name = "training_plan_exercise", schema = "training_plan")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainingPlanExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "training_plan_exercise_sequence_generator")
    @SequenceGenerator(name = "training_plan_exercise_sequence_generator",
            sequenceName = "training_plan_exercise_id_seq",
            allocationSize = 1,
            schema = "training_plan")
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MeasurementUnit measurementUnit;

//    @Column(nullable = false)
//    private Long exerciseSequenceNumber;

    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @OneToMany(mappedBy = "trainingPlanExercise", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExerciseSet> exerciseSets;

    @ManyToOne
    @JoinColumn(name = "training_plan_id", nullable = false)
    private TrainingPlan trainingPlan;

}

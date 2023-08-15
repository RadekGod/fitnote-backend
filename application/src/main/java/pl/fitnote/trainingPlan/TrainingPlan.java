package pl.fitnote.trainingPlan;


import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
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
import pl.fitnote.user.User;

import java.util.List;

@Entity
@Table(name = "training_plan", schema = "training_plan")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainingPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "training_plan_sequence_generator")
    @SequenceGenerator(name = "training_plan_sequence_generator",
            sequenceName = "training_plan_id_seq",
            allocationSize = 1,
            schema = "training_plan")
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Convert(converter = TrainingDaysConverter.class)
    private List<TrainingDay> trainingDays;

    private String note;

    @OneToMany(mappedBy = "trainingPlan")
    private List<TrainingPlanExercise> trainingPlanExercises;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

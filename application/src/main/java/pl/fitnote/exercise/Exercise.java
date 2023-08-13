package pl.fitnote.exercise;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import pl.fitnote.user.User;

import java.util.List;

@Entity
@Table(name = "exercise", schema = "training")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Exercise {

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

    private String description;

    @Convert(converter = InvolvedMusclesConverter.class)
    private List<InvolvedMuscles> mainMuscles;

    @Convert(converter = InvolvedMusclesConverter.class)
    private List<InvolvedMuscles> supportiveMuscles;

    @Convert(converter = ExerciseCategoryGroupConverter.class)
    private List<ExerciseCategoryGroup> exerciseCategoryGroups;

    @Enumerated(EnumType.STRING)
    private ExerciseType exerciseType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

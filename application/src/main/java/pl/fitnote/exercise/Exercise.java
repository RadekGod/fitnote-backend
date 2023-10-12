package pl.fitnote.exercise;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.fitnote.commons.file.ApplicationFile;
import pl.fitnote.user.User;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "exercise", schema = "training_plan")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exercise_sequence_generator")
    @SequenceGenerator(name = "exercise_sequence_generator",
            sequenceName = "exercise_id_seq",
            allocationSize = 1,
            schema = "training_plan")
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Convert(converter = InvolvedMusclesConverter.class)
    private List<InvolvedMuscles> mainMuscles;

    @Convert(converter = InvolvedMusclesConverter.class)
    private List<InvolvedMuscles> supportiveMuscles;

    private Boolean custom;



    @ManyToMany
    @JoinTable(
            name = "exercise_exercise_category_group",
            schema = "training_plan",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_category_group_id"))
    private Set<ExerciseCategoryGroup> exerciseCategoryGroups;

    @Enumerated(EnumType.STRING)
    private ExerciseType exerciseType;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn
    private ApplicationFile applicationFile;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

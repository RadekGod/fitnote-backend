package pl.fitnote.exercise;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "exercise_category_group", schema = "training_plan")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ExerciseCategoryGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exercise_category_group_sequence_generator")
    @SequenceGenerator(name = "exercise_category_group_sequence_generator",
            sequenceName = "exercise_category_group_id_seq",
            allocationSize = 1,
            schema = "training_plan")
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ExerciseCategoryGroupEnum categoryName;

    @ManyToMany(mappedBy = "exerciseCategoryGroups", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Exercise> exercises;
}

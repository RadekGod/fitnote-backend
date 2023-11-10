package pl.fitnote.training;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import pl.fitnote.user.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "training", schema = "training_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "training_sequence_generator")
    @SequenceGenerator(name = "training_sequence_generator",
            sequenceName = "training_id_seq",
            allocationSize = 1,
            schema = "training_history")
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String note;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime finishTime;

    @OneToMany(mappedBy = "training", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TrainingExercise> trainingExercises;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

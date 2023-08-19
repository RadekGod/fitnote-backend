package pl.fitnote.sleep;

import jakarta.persistence.*;
import lombok.*;
import pl.fitnote.user.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "sleep", schema = "sleep")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Sleep {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sleep_sequence_generator")
    @SequenceGenerator(name = "sleep_sequence_generator",
            sequenceName = "sleep_id_seq",
            allocationSize = 1,
            schema = "sleep")
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;
    private Float rating;
    private Integer awakeningsCount;
    private String note;

    @Column(nullable = false)
    private LocalDateTime measurementDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

package pl.fitnote.sleep;

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
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    private Float rating;
    private Integer awakeningsNumber;
    private String note;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

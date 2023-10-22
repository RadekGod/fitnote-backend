package pl.fitnote.activity;

import jakarta.persistence.*;
import lombok.*;
import pl.fitnote.user.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "activity", schema = "activity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_sequence_generator")
    @SequenceGenerator(name = "activity_sequence_generator",
            sequenceName = "activity_id_seq",
            allocationSize = 1,
            schema = "activity")
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;
    private Float activityDurationInMinutes;
    private Integer burntCalories;
    private Float distanceTraveled;

    @Column(nullable = false)
    private LocalDateTime activityDate;

    @ManyToOne
    @JoinColumn(name = "activity_type_id", nullable = false)
    private ActivityType activityType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}


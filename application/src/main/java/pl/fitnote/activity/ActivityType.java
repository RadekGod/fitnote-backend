package pl.fitnote.activity;

import jakarta.persistence.*;
import lombok.*;
import pl.fitnote.user.User;

import java.util.Set;

@Entity
@Table(name = "activity_type", schema = "activity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ActivityType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_type_sequence_generator")
    @SequenceGenerator(name = "activity_type_sequence_generator",
            sequenceName = "activity_type_id_seq",
            allocationSize = 1,
            schema = "activity")
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;
    private String name;
    private Float averageCaloriesBurntPerHour;

    @Column(nullable = false)
    private Boolean distanceActivity;

    @OneToMany(mappedBy="activityType")
    private Set<Activity> activities;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}


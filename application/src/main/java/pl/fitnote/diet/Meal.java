package pl.fitnote.diet;

import jakarta.persistence.*;
import lombok.*;
import pl.fitnote.activity.ActivityType;
import pl.fitnote.user.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "meal", schema = "diet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meal_sequence_generator")
    @SequenceGenerator(name = "meal_sequence_generator",
            sequenceName = "meal_id_seq",
            allocationSize = 1,
            schema = "diet")
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;
    @Column(nullable = false)
    private String name;
    private Float kilocalories;
    private Float proteins;
    private Float carbohydrates;
    private Float fat;
    private Float salt;

    @Column(nullable = false)
    private LocalDateTime mealDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}


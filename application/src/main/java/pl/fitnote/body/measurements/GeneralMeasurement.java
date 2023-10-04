package pl.fitnote.body.measurements;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.fitnote.user.LengthUnit;
import pl.fitnote.user.User;
import pl.fitnote.user.WeightUnit;

import java.time.LocalDateTime;

@Entity
@Table(name = "general_measurement", schema = "body")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class GeneralMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "general_measurement_sequence_generator")
    @SequenceGenerator(name = "general_measurement_sequence_generator",
            sequenceName = "general_measurement_id_seq",
            allocationSize = 1,
            schema = "body")
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private Float weight;

    @Column(nullable = false)
    private Float height;

    @Column(nullable = false)
    private Float bmi;

    private Float muscleContent;

    private Float bodyFat;

    @Enumerated(EnumType.STRING)
    private WeightUnit weightUnit;

    @Enumerated(EnumType.STRING)
    private LengthUnit lengthUnit;

    @Column(nullable = false)
    private LocalDateTime measurementDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

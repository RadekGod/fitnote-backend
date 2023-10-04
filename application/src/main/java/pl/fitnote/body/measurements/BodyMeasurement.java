package pl.fitnote.body.measurements;

import jakarta.persistence.*;
import lombok.*;
import pl.fitnote.user.LengthUnit;
import pl.fitnote.user.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "body_measurement", schema = "body")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BodyMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "body_measurement_sequence_generator")
    @SequenceGenerator(name = "body_measurement_sequence_generator",
            sequenceName = "body_measurement_id_seq",
            allocationSize = 1,
            schema = "body")
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;
    private Float chest;
    private Float bicepsLeft;
    private Float bicepsRight;
    private Float forearmLeft;
    private Float forearmRight;
    private Float waist;
    private Float hip;
    private Float thighLeft;
    private Float thighRight;
    private Float calfLeft;
    private Float calfRight;

    @Enumerated(EnumType.STRING)
    private LengthUnit lengthUnit;

    @Column(nullable = false)
    private LocalDateTime measurementDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}


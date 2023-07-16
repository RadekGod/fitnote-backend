package pl.fitnote.user;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_settings", schema = "user_management")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
class UserSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_settings_sequence_generator")
    @SequenceGenerator(name = "user_settings_sequence_generator",
            sequenceName = "user_settings_id_seq",
            allocationSize = 1,
            schema = "user_management")
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;

    @Enumerated(EnumType.STRING)
    private WeightUnit weightUnit;

    @Enumerated(EnumType.STRING)
    private LengthUnit lengthUnit;
}

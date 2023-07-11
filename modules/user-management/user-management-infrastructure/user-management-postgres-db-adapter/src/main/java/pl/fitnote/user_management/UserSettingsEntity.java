package pl.fitnote.user_management;


import jakarta.persistence.*;
import lombok.*;
import pl.fitnote.user_management.model.user.vo.LengthUnit;
import pl.fitnote.user_management.model.user.vo.WeightUnit;

@Entity
@Table(name = "user_settings", schema = "user_management")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
class UserSettingsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_settings_sequence_generator")
    @SequenceGenerator(name = "user_settings_sequence_generator",
            sequenceName = "user_settings_id_seq",
            allocationSize = 1,
            schema = "user_management")
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;

    private String language;

    @Enumerated(EnumType.STRING)
    private WeightUnit weight_unit;

    @Enumerated(EnumType.STRING)
    private LengthUnit length_unit;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userEntry;

}

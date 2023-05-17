package pl.fitnote.user_management;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_settings", schema = "user_details")
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
            schema = "user_details")
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;

    private String language;

    @Enumerated(EnumType.STRING)
    private WeightUnit weight_unit;

    @Enumerated(EnumType.STRING)
    private LengthUnit length_unit;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntryEntity userEntry;

}

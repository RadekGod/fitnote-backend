package pl.fitnote.user_management;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.Instant;

@Entity
@Table(name = "user", schema = "user_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
class UserEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence_generator")
    @SequenceGenerator(name = "user_sequence_generator",
            sequenceName = "user_id_seq",
            allocationSize = 1,
            schema = "user_details")
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(nullable = false, updatable = false, unique = true)
    private String keycloakId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Instant creationTime;

    @Column(nullable = false)
    private Boolean enabled;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(mappedBy = "userEntry")
    private UserSettingsEntity userSettings;
}
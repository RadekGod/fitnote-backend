package pl.fitnote.user;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.Instant;

@Entity
@Table(name = "user_details", schema = "user_management")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_details_sequence_generator")
    @SequenceGenerator(name = "user_details_sequence_generator",
            sequenceName = "user_details_id_seq",
            allocationSize = 1,
            schema = "user_management")
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(nullable = false, updatable = false, unique = true)
    private String keycloakId;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private UserSettings userSettings;
}
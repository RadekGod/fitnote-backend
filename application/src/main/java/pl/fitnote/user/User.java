package pl.fitnote.user;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.fitnote.body.BodyMeasurement;
import pl.fitnote.body.GeneralMeasurement;
import pl.fitnote.exercise.Exercise;
import pl.fitnote.trainingPlan.TrainingPlan;

import java.sql.Date;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "user_details", schema = "user_management")
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

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

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private Set<Exercise> exercises;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private Set<BodyMeasurement> bodyMeasurements;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private Set<GeneralMeasurement> generalBodyData;


    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private Set<TrainingPlan> trainingPlans;
}
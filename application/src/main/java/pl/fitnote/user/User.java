package pl.fitnote.user;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.fitnote.body.gallery.GalleryPhoto;
import pl.fitnote.body.measurements.bodyMasurement.BodyMeasurement;
import pl.fitnote.body.measurements.generalMeasurement.GeneralMeasurement;
import pl.fitnote.exercise.Exercise;
import pl.fitnote.sleep.Sleep;
import pl.fitnote.trainingPlan.TrainingPlan;

import java.sql.Date;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "user_details", schema = "user_management")
@Getter
@Setter
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

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Instant creationTime;

    @Column(nullable = false)
    private Boolean enabled;

    @Column()
    private String firstName;

    @Column()
    private String lastName;

    @Column()
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private UserSettings userSettings;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authority",
            schema = "user_management",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private Set<Authority> userAuthorities;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Exercise> exercises;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private Set<BodyMeasurement> bodyMeasurements;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private Set<GeneralMeasurement> generalMeasurements;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private Set<GalleryPhoto> galleryPhotos;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private Set<Sleep> sleeps;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TrainingPlan> trainingPlans;
}
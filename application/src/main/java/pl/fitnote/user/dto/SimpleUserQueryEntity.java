package pl.fitnote.user.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_details", schema = "user_management")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimpleUserQueryEntity {
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
}

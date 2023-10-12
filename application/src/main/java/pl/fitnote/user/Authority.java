package pl.fitnote.user;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "authority", schema = "user_management")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authority_sequence_generator")
    @SequenceGenerator(name = "authority_sequence_generator",
            sequenceName = "authority_id_seq",
            allocationSize = 1,
            schema = "user_management")
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;

    private String name;
    private Boolean defaultAuthority;

    @ManyToMany(mappedBy = "userAuthorities", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<User> users;
}

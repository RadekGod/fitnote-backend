package pl.fitnote.commons.file;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "application_file", schema = "application_file")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ApplicationFile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "application_file_sequence_generator")
    @SequenceGenerator(name = "application_file_sequence_generator",
            sequenceName = "application_file_id_seq",
            allocationSize = 1,
            schema = "application_file")
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @Lob
    @Column(nullable = false)
    private byte[] data;
}


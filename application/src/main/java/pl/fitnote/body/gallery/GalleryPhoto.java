package pl.fitnote.body.gallery;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.fitnote.commons.file.ApplicationFile;
import pl.fitnote.user.User;

@Entity
@Table(name = "gallery_photo", schema = "body")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class GalleryPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gallery_photo_sequence_generator")
    @SequenceGenerator(name = "gallery_photo_sequence_generator",
            sequenceName = "gallery_photo_id_seq",
            allocationSize = 1,
            schema = "body")
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;
    private String note;

//    @Column(nullable = false)
//    private LocalDateTime creationDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private ApplicationFile applicationFile;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}


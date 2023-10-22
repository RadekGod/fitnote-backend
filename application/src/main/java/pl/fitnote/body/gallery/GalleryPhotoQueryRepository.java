package pl.fitnote.body.gallery;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface GalleryPhotoQueryRepository extends JpaRepository<GalleryPhoto, Long> {
    <T> List<T> findAllByUserEmailOrderByApplicationFileCreationDateDesc(String email, Class<T> type);

    <T> Optional<T> findByIdAndUserEmail(Long id, String email, Class<T> type);

    <T> Optional<T> findTop1ByUserEmailOrderByApplicationFileCreationDateDesc(String email, Class<T> type);
}

package pl.fitnote.body.gallery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface GalleryPhotoQueryRepository extends JpaRepository<GalleryPhoto, Long> {
    @Query(value = "select gp from GalleryPhoto gp where gp.user.email = :email")
    List<GalleryPhotoProjection> findAllGalleryPhotosByGivenEmail(@Param("email") String email);


    @Query(value = "select gp from GalleryPhoto gp where gp.user.email = :email order by gp.applicationFile.creationDate desc")
    List<SimpleGalleryPhotoProjection> findAllGalleryPhotoDescriptionsByGivenEmail(@Param("email") String email);

    <T> List<T> findAllByUserEmailOrderByApplicationFileCreationDateDesc(String email, Class<T> type);


//    @Query(value = "select gp from GalleryPhoto gp join fetch gp.applicationFile join fetch gp.user where gp.user.email = :email order by gp.applicationFile.creationDate desc")
//    List<SimpleGalleryPhotoProjection> findAllGalleryPhotoDescriptionsByGivenEmail(@Param("email") String email);

//    @Query(value = "select gp from GalleryPhoto gp where gp.user.email = :email order by gp.applicationFile.creationDate desc LIMIT 1")
//    Optional<SimpleGalleryPhotoProjection> findLatestGalleryPhotoDescriptionByGivenEmail(@Param("email") String email);

    <T> Optional<T> findTop1ByUserEmailOrderByApplicationFileCreationDateDesc(String email, Class<T> type);
}

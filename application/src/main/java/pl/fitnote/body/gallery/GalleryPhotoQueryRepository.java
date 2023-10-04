package pl.fitnote.body.gallery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface GalleryPhotoQueryRepository extends JpaRepository<GalleryPhoto, Long> {
//    @Query(value = "select gm from GeneralMeasurement gm where gm.id = :generalMeasurementId and gm.user.email = :email")
//    <T> Optional<T> findGeneralMeasurementByGivenIdAndEmail(@Param("generalMeasurementId") Long generalMeasurementId, @Param("email") String email, Class<T> type);
//
//    @Query(value = "select gm from GeneralMeasurement gm where gm.user.email = :email order by gm.measurementDate desc, gm.id desc LIMIT 1")
//    <T> Optional<T> findLatestGeneralMeasurementByGivenEmail( @Param("email") String email, Class<T> type);
//
//
//
    @Query(value = "select gp from GalleryPhoto gp where gp.user.email = :email")
    List<GalleryPhotoProjection> findAllGalleryPhotosByGivenEmail(@Param("email") String email);
}

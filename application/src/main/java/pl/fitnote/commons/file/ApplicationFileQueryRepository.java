package pl.fitnote.commons.file;

import org.springframework.data.jpa.repository.JpaRepository;

interface ApplicationFileQueryRepository extends JpaRepository<ApplicationFile, Long> {
//    @Query(value = "select gm from GeneralMeasurement gm where gm.id = :generalMeasurementId and gm.user.email = :email")
//    <T> Optional<T> findGeneralMeasurementByGivenIdAndEmail(@Param("generalMeasurementId") Long generalMeasurementId, @Param("email") String email, Class<T> type);
//
//    @Query(value = "select gm from GeneralMeasurement gm where gm.user.email = :email order by gm.measurementDate desc, gm.id desc LIMIT 1")
//    <T> Optional<T> findLatestGeneralMeasurementByGivenEmail( @Param("email") String email, Class<T> type);
//
//
//
//    @Query(value = "select gm from GeneralMeasurement gm where gm.user.email = :email")
//    List<GalleryPhotoProjection> findAllGeneralMeasurementsByGivenEmail(@Param("email") String email);
}

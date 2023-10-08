package pl.fitnote.body.measurements;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface BodyMeasurementQueryRepository extends JpaRepository<BodyMeasurement, Long> {
//    @Query(value = "select bm from BodyMeasurement bm where bm.id = :bodyMeasurementId and bm.user.email = :email")
//    <T> Optional<T> findBodyMeasurementByGivenIdAndEmail(@Param("bodyMeasurementId") Long bodyMeasurementId, @Param("email") String email, Class<T> type);

    <T> Optional<T> findByIdAndUserEmail(@Param("bodyMeasurementId") Long bodyMeasurementId, @Param("email") String email, Class<T> type);

    <T> Optional<T> findTop1ByUserEmailOrderByMeasurementDateDesc( @Param("email") String email, Class<T> type);


//    @Query(value = "select bm from BodyMeasurement bm where bm.user.email = :email")
//    List<BodyMeasurementProjection> findAllBodyMeasurementsByGivenEmail(@Param("email") String email);
    List<BodyMeasurementProjection> findAllByUserEmail(@Param("email") String email);
}

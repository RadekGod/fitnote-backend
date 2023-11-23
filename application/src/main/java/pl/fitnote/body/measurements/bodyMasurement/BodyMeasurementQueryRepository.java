package pl.fitnote.body.measurements.bodyMasurement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface BodyMeasurementQueryRepository extends JpaRepository<BodyMeasurement, Long> {
    <T> Optional<T> findByIdAndUserEmail(@Param("bodyMeasurementId") Long bodyMeasurementId, @Param("email") String email, Class<T> type);

    <T> Optional<T> findTop1ByUserEmailOrderByMeasurementDateDescIdDesc(@Param("email") String email, Class<T> type);

    List<BodyMeasurementProjection> findAllByUserEmail(@Param("email") String email);
}

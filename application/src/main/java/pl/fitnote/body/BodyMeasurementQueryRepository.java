package pl.fitnote.body;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface BodyMeasurementQueryRepository extends JpaRepository<BodyMeasurement, Long> {
    @Query(value = "select bm from BodyMeasurement bm where bm.id = :bodyMeasurementId and bm.user.keycloakId = :keycloakId")
    <T> Optional<T> findBodyMeasurementByGivenIdAndKeycloakId(@Param("bodyMeasurementId") Long bodyMeasurementId, @Param("keycloakId") String keycloakId, Class<T> type);

    @Query(value = "select bm from BodyMeasurement bm where bm.user.keycloakId = :keycloakId")
    List<BodyMeasurementProjection> findAllBodyMeasurementsByGivenKeycloakId(@Param("keycloakId") String keycloakId);
}

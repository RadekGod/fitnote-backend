package pl.fitnote.body;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface GeneralMeasurementQueryRepository  extends JpaRepository<GeneralMeasurement, Long> {
    @Query(value = "select gm from GeneralMeasurement gm where gm.id = :generalMeasurementId and gm.user.keycloakId = :keycloakId")
    <T> Optional<T> findGeneralMeasurementByGivenIdAndKeycloakId(@Param("generalMeasurementId") Long generalMeasurementId, @Param("keycloakId") String keycloakId, Class<T> type);

    @Query(value = "select gm from GeneralMeasurement gm where gm.user.keycloakId = :keycloakId")
    List<GeneralMeasurementProjection> findAllGeneralMeasurementsByGivenKeycloakId(@Param("keycloakId") String keycloakId);
}

package pl.fitnote.sleep;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface SleepQueryRepository extends JpaRepository<Sleep, Long> {
    @Query(value = "select s from Sleep s where s.id = :sleepId and s.user.keycloakId = :keycloakId")
    <T> Optional<T> findSleepByGivenIdAndKeycloakId(@Param("sleepId") Long bodyMeasurementId, @Param("keycloakId") String keycloakId, Class<T> type);

    @Query(value = "select s from Sleep s where s.user.keycloakId = :keycloakId")
    List<SleepProjection> findAllSleepsByGivenKeycloakId(@Param("keycloakId") String keycloakId);
}

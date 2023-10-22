package pl.fitnote.sleep;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface SleepQueryRepository extends JpaRepository<Sleep, Long> {
    <T> Optional<T> findByIdAndUserEmail(@Param("sleepId") Long bodyMeasurementId, @Param("email") String email, Class<T> type);

    List<SleepProjection> findAllByUserEmail(@Param("email") String email);
}

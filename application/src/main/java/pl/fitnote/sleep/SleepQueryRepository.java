package pl.fitnote.sleep;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface SleepQueryRepository extends JpaRepository<Sleep, Long> {
    @Query(value = "select s from Sleep s where s.id = :sleepId and s.user.email = :email")
    <T> Optional<T> findSleepByGivenIdAndEmail(@Param("sleepId") Long bodyMeasurementId, @Param("email") String email, Class<T> type);

    @Query(value = "select s from Sleep s where s.user.email = :email")
    List<SleepProjection> findAllSleepsByGivenEmail(@Param("email") String email);
}

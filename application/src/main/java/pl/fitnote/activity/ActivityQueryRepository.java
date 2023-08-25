package pl.fitnote.activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface ActivityQueryRepository extends JpaRepository<Activity, Long> {
    @Query(value = "select a from Activity a where a.id = :activityId and a.user.keycloakId = :keycloakId")
    <T> Optional<T> findActivityByGivenIdAndKeycloakId(@Param("activityId") Long activityId, @Param("keycloakId") String keycloakId, Class<T> type);

    @Query(value = "select a from Activity a where a.user.keycloakId = :keycloakId")
    List<ActivityProjection> findAllActivitiesByGivenKeycloakId(@Param("keycloakId") String keycloakId);
}

package pl.fitnote.activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface ActivityTypeQueryRepository extends JpaRepository<ActivityType, Long> {
    @Query(value = "select at from ActivityType at where at.id = :activityTypeId and (at.user.keycloakId = :keycloakId or at.user is null)")
    <T> Optional<T> findActivityTypeByGivenIdAndKeycloakId(@Param("activityTypeId") Long activityTypeId, @Param("keycloakId") String keycloakId, Class<T> type);

    @Query(value = "select at from ActivityType at where at.user.keycloakId = :keycloakId or at.user is null")
    List<ActivityTypeProjection> findAllActivityTypesForUser(@Param("keycloakId") String keycloakId);

    @Query(value = "select at from ActivityType at where at.id = :activityTypeId and at.user.keycloakId = :keycloakId")
    <T> Optional<T> findCustomActivityTypeByGivenIdAndKeycloakId(@Param("activityTypeId") Long activityTypeId, @Param("keycloakId") String keycloakId, Class<T> type);

}

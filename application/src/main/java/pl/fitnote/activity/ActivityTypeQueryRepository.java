package pl.fitnote.activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface ActivityTypeQueryRepository extends JpaRepository<ActivityType, Long> {
    @Query(value = "select at from ActivityType at where at.id = :activityTypeId and (at.user.email = :email or at.user is null)")
    <T> Optional<T> findActivityTypeByGivenIdAndEmail(@Param("activityTypeId") Long activityTypeId, @Param("email") String email, Class<T> type);

    @Query(value = "select at from ActivityType at where at.user.email = :email or at.user is null")
    List<ActivityTypeProjection> findAllActivityTypesForUser(@Param("email") String email);

    @Query(value = "select at from ActivityType at where at.id = :activityTypeId and at.user.email = :email")
    <T> Optional<T> findCustomActivityTypeByGivenIdAndEmail(@Param("activityTypeId") Long activityTypeId, @Param("email") String email, Class<T> type);

}

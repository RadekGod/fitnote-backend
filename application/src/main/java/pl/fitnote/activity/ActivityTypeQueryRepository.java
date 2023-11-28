package pl.fitnote.activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface ActivityTypeQueryRepository extends JpaRepository<ActivityType, Long> {
    @Query(value = "select at from ActivityType at where at.id = :activityTypeId")
    <T> Optional<T> findActivityTypeByGivenIdAndEmail(@Param("activityTypeId") Long activityTypeId, Class<T> type);
    List<ActivityTypeProjection> findAllByUserEmailOrUserIsNull(@Param("email") String email);

    @Query(value = "select at from ActivityType at where at.id = :activityTypeId and at.user.email = :email")
    <T> Optional<T> findCustomActivityTypeByGivenIdAndEmail(@Param("activityTypeId") Long activityTypeId, @Param("email") String email, Class<T> type);

}

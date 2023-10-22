package pl.fitnote.activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface ActivityQueryRepository extends JpaRepository<Activity, Long> {
    @Query(value = "select a from Activity a where a.id = :activityId and a.user.email = :email")
    <T> Optional<T> findActivityByGivenIdAndEmail(@Param("activityId") Long activityId, @Param("email") String email, Class<T> type);

    @Query(value = "select a from Activity a where a.user.email = :email")
    List<ActivityProjection> findAllActivitiesByGivenEmail(@Param("email") String email);
}

package pl.fitnote.activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface ActivityQueryRepository extends JpaRepository<Activity, Long> {
    <T> Optional<T> findByIdAndUserEmail(@Param("activityId") Long activityId, @Param("email") String email, Class<T> type);

    List<ActivityProjection> findAllByUserEmailOrderByActivityDateDesc(@Param("email") String email);
}

package pl.fitnote.diet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.fitnote.activity.Activity;
import pl.fitnote.activity.ActivityProjection;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

interface MealQueryRepository extends JpaRepository<Meal, Long> {
    <T> Optional<T> findByIdAndUserEmail(@Param("activityId") Long activityId, @Param("email") String email, Class<T> type);

    @Query(value = "select m from Meal m where m.user.email = :email and m.mealDate between :timeFrom and :timeTo order by m.mealDate desc")
    List<MealProjection> findAllTodayMeals(@Param("email") String email, @Param("timeFrom") LocalDateTime timeFrom, @Param("timeTo") LocalDateTime timeTo);

}

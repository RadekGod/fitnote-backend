package pl.fitnote.exercise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface ExerciseQueryRepository extends JpaRepository<Exercise, Long> {

    @Query(value = "select e from Exercise e left join e.user u where u.email = :email or u is null")
    List<ExerciseProjection> findAllExercisesForUserByEmail(@Param("email") String email);

    @Query(value = "select e from Exercise e left join e.user u where e.id = :exerciseId and (u is null or u.email = :email)")
    <T> Optional<T> findExerciseForUserByEmail(@Param("exerciseId") Long exerciseId, @Param("email") String email, Class<T> type);
}

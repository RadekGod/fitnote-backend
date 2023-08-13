package pl.fitnote.exercise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.fitnote.exercise.dto.ExerciseProjection;

import java.util.List;
import java.util.Optional;

interface ExerciseQueryRepository extends JpaRepository<Exercise, Long> {

    @Query(value = "select e from Exercise e left join e.user u where u.keycloakId = :keycloakId or u is null")
    List<ExerciseProjection> findAllExercisesForUserByKeycloakId(@Param("keycloakId") String keycloakId);

    @Query(value = "select e from Exercise e left join e.user u where e.id = :exerciseId and (u is null or u.keycloakId = :keycloakId)")
    <T> Optional<T> findExerciseForUserByKeycloakId(@Param("exerciseId") Long exerciseId, @Param("keycloakId") String keycloakId, Class<T> type);
}

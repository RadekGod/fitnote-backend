package pl.fitnote.exerciseSet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface ExerciseSetQueryRepository extends JpaRepository<ExerciseSet, Long> {

    @Query(value = "select es from ExerciseSet es join es.trainingPlan tp join es.exercise e join tp.user u " +
            "where es.exercise.id = :exerciseId and tp.id = :trainingPlanId and u.keycloakId = :keycloakId")
    List<ExerciseSet> findAllExerciseSetsOfGivenExerciseForTrainingId(@Param("exerciseId") Long exerciseId, @Param("trainingPlanId") Long trainingPlanId, @Param("keycloakId") String keycloakId);


    @Query(value = "select tp from TrainingPlan tp left join tp.user u where tp.id = :trainingPlanId and u.keycloakId = :keycloakId")
    <T> Optional<T> findTrainingPlanForUserByKeycloakId(@Param("trainingPlanId") Long trainingPlanId, @Param("keycloakId") String keycloakId, Class<T> type);

}

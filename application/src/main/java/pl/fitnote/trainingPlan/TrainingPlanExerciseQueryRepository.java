package pl.fitnote.trainingPlan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface TrainingPlanExerciseQueryRepository extends JpaRepository<TrainingPlanExercise, Long> {

    @Query(value = "select tpe from TrainingPlanExercise tpe where tpe.id = :trainingPlanExerciseId and tpe.trainingPlan.user.keycloakId = :keycloakId")
    Optional<TrainingPlanExerciseProjection> findTrainingPlanExerciseOfGivenTrainingPlan(@Param("trainingPlanExerciseId") Long trainingPlanExerciseId, @Param("keycloakId") String keycloakId);
    @Query(value = "select tpe from TrainingPlanExercise tpe where tpe.trainingPlan.id = :trainingPlanId and tpe.trainingPlan.user.keycloakId = :keycloakId")
    List<TrainingPlanExerciseProjection> findAllTrainingPlanExercisesOfGivenTrainingPlan(@Param("trainingPlanId") Long trainingPlanId, @Param("keycloakId") String keycloakId);
    @Query(value = "select tpe.exerciseSets from TrainingPlanExercise tpe where tpe.id = :trainingPlanExerciseId and tpe.trainingPlan.user.keycloakId = :keycloakId")
    List<ExerciseSet> findAllExerciseSetsOfGivenTrainingPlanExerciseId(@Param("trainingPlanExerciseId") Long trainingPlanExerciseId, @Param("keycloakId") String keycloakId);
}

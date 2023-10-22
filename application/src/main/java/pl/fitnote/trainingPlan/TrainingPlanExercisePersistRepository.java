package pl.fitnote.trainingPlan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

interface TrainingPlanExercisePersistRepository extends JpaRepository<TrainingPlanExercise, Long> {
    @Modifying
    @Query(value = "delete from TrainingPlanExercise tpe where tpe.id = :trainingPlanExerciseId")
    void deleteTrainingPlanExerciseById(@Param("trainingPlanExerciseId") Long trainingPlanExerciseId);
}

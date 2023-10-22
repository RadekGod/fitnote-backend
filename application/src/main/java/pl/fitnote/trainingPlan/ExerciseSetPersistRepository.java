package pl.fitnote.trainingPlan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface ExerciseSetPersistRepository extends JpaRepository<ExerciseSet, Long> {
    @Modifying
    @Query(value = "delete from ExerciseSet es where es.id in :trainingPlanExerciseIds")
    void deleteExerciseSetsByIds(@Param("trainingPlanExerciseIds") List<Long> trainingPlanExerciseIds);
}

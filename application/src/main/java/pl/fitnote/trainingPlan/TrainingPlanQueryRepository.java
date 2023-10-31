package pl.fitnote.trainingPlan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface TrainingPlanQueryRepository extends JpaRepository<TrainingPlan, Long> {

    List<SimpleTrainingPlanProjection> findAllByUserEmailOrderByIdAsc(@Param("email") String email);
    SimpleTrainingPlanProjection findByIdAndUserEmail(@Param("trainingPlanId") Long trainingPlanId, @Param("email") String email);
    <T> Optional<T> findByIdAndUserEmail(@Param("trainingPlanId") Long trainingPlanId, @Param("email") String email, Class<T> type);
}

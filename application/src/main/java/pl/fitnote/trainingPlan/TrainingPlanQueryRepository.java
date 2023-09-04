package pl.fitnote.trainingPlan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface TrainingPlanQueryRepository extends JpaRepository<TrainingPlan, Long> {

    @Query(value = "select tp from TrainingPlan tp left join tp.user u where u.email = :email")
    List<TrainingPlanProjection> findAllTrainingPlansForUserByEmail(@Param("email") String email);

    @Query(value = "select tp from TrainingPlan tp left join tp.user u where tp.id = :trainingPlanId and u.email = :email")
    <T> Optional<T> findTrainingPlanForUserByEmail(@Param("trainingPlanId") Long trainingPlanId, @Param("email") String email, Class<T> type);
}

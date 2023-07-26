package pl.fitnote.trainingPlan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface TrainingPlanQueryRepository extends JpaRepository<TrainingPlan, Long> {

    @Query(value = "select tp from TrainingPlan tp left join tp.user u where u.keycloakId = :keycloakId")
    List<TrainingPlanProjection> findAllTrainingPlansForUserByKeycloakId(@Param("keycloakId") String keycloakId);

    @Query(value = "select tp from TrainingPlan tp left join tp.user u where tp.id = :trainingPlanId and u.keycloakId = :keycloakId")
    <T> Optional<T> findTrainingPlanForUserByKeycloakId(@Param("trainingPlanId") Long trainingPlanId, @Param("keycloakId") String keycloakId, Class<T> type);
}

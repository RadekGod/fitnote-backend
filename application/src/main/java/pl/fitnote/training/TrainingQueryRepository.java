package pl.fitnote.training;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface TrainingQueryRepository extends JpaRepository<Training, Long> {

    @Query(value = "select t from Training t where t.id = :trainingId and t.user.keycloakId = :keycloakId")
    <T> Optional<T> findTrainingByGivenIdAndKeycloakId(@Param("trainingId") Long trainingId, @Param("keycloakId") String keycloakId, Class<T> type);

    @Query(value = "select t from Training t where t.user.keycloakId = :keycloakId")
    List<TrainingProjection> findAllTrainingsByGivenKeycloakId(@Param("keycloakId") String keycloakId);
}

package pl.fitnote.training;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface TrainingQueryRepository extends JpaRepository<Training, Long> {

    @Query(value = "select t from Training t where t.id = :trainingId and t.user.email = :email")
    <T> Optional<T> findTrainingByGivenIdAndEmail(@Param("trainingId") Long trainingId, @Param("email") String email, Class<T> type);

    @Query(value = "select t from Training t where t.user.email = :email")
    List<TrainingProjection> findAllTrainingsByGivenEmail(@Param("email") String email);
}

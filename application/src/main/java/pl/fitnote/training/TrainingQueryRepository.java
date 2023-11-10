package pl.fitnote.training;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface TrainingQueryRepository extends JpaRepository<Training, Long> {
    <T> Optional<T> findByIdAndUserEmail(@Param("trainingId") Long trainingId, @Param("email") String email, Class<T> type);
    List<SimpleTrainingProjection> findAllByUserEmailOrderByStartTimeDesc(@Param("email") String email);
}

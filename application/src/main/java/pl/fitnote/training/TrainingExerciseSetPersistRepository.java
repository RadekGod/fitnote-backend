package pl.fitnote.training;

import org.springframework.data.jpa.repository.JpaRepository;

interface TrainingExerciseSetPersistRepository extends JpaRepository<TrainingExerciseSet, Long> {
}

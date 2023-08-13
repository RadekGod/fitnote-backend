package pl.fitnote.trainingPlan;

import org.springframework.data.jpa.repository.JpaRepository;

interface ExerciseSetPersistRepository extends JpaRepository<ExerciseSet, Long> {
}
